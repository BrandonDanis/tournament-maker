package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TournamentMain extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    String tournamentName;
    TextView tourneyNameLabel;

    public static List<Player> players = new ArrayList<Player>();

    String jsonSTR = null;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tourneyNameLabel = (TextView)findViewById(R.id.tourneyName);

        listView = (ListView)findViewById(R.id.playerList);

        tournamentName = bundle.getString("Tournament Name");

        tourneyNameLabel.setText(tournamentName);

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("data.json")));
            jsonSTR = JSONreader.getJSONstring(br);
        }catch (IOException e){
            e.printStackTrace();
        }

        initPlayers();
    }

    private void initPlayers(){

        JSONObject tournament = JSONreader.getTournamentByName(jsonSTR, tournamentName);

        //empty the player array before appending them
        players.clear();

        try{
            JSONArray playersARR = tournament.getJSONArray("players");

            for(int i=0; i<playersARR.length(); i++){
                Player player = new Player();
                player.setName(playersARR.getJSONObject(i).getString("name"));
                player.setTeamName(playersARR.getJSONObject(i).getString("team"));
                player.setRanking(Integer.parseInt(playersARR.getJSONObject(i).getString("rank")));
                player.setGamesWon(Integer.parseInt(playersARR.getJSONObject(i).getString("wins")));
                player.setGamesPlayed(Integer.parseInt(playersARR.getJSONObject(i).getString("games")));
                players.add(player);
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }

        populatePlayers();

    }

    private void populatePlayers(){

        String[] names = new String[players.size()];
        String[] teams = new String[players.size()];
        int[] rankings = new int[players.size()];

        for(int i=0; i<players.size(); i++){
            names[i] = players.get(i).getName();
            teams[i] = players.get(i).getTeamName();
            rankings[i] = players.get(i).getRanking();
        }

        MySimpleArrayAdapter arrayAdapter = new MySimpleArrayAdapter(getApplicationContext(), names, teams, rankings);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(TournamentMain.this,PlayerInfo.class);
        intent.putExtra("Tournament Name", tournamentName);
        intent.putExtra("Player Name", players.get(position).getName());
        intent.putExtra("Player Team", players.get(position).getTeamName());
        intent.putExtra("Player Ranking", players.get(position).getRanking());
        intent.putExtra("Player Games", players.get(position).getGamesPlayed());
        intent.putExtra("Player Wins", players.get(position).getGamesWon());
        this.startActivity(intent);

    }

}
