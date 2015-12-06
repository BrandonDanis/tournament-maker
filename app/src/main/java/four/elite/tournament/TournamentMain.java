package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class TournamentMain extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    public Tournament tournament;

    TextView tourneyNameLabel;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tourneyNameLabel = (TextView)findViewById(R.id.tourneyName);
        listView = (ListView)findViewById(R.id.playerList);

        tournament = DataManager.getTournamentByName(getApplicationContext(),bundle.getString("Tournament Name"));
        tourneyNameLabel.setText(tournament.getName());

        populatePlayers();
    }

    private void populatePlayers(){

        String[] names = new String[tournament.getPlayers().size()];
        String[] teams = new String[tournament.getPlayers().size()];
        String[] urls = new String[tournament.getPlayers().size()];
        int[] rankings = new int[tournament.getPlayers().size()];

        for(int i=0; i< tournament.getPlayers().size(); i++){
            names[i] = tournament.getPlayers().get(i).getName();
            teams[i] = tournament.getPlayers().get(i).getTeamName();
            rankings[i] = tournament.getPlayers().get(i).getRanking();
            urls[i] = tournament.getPlayers().get(i).getImageUrl();
        }

        MySimpleArrayAdapter arrayAdapter = new MySimpleArrayAdapter(getApplicationContext(), names, teams, rankings, urls);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(TournamentMain.this,PlayerInfo.class);
        intent.putExtra("Tournament Name", tournament.getName());
        intent.putExtra("Player Name", tournament.getPlayers().get(position).getName());
        intent.putExtra("Player Team", tournament.getPlayers().get(position).getTeamName());
        intent.putExtra("Player Ranking", tournament.getPlayers().get(position).getRanking());
        intent.putExtra("Player Games", tournament.getPlayers().get(position).getGamesPlayed());
        intent.putExtra("Player Wins", tournament.getPlayers().get(position).getGamesWon());
        intent.putExtra("Image URL", tournament.getPlayers().get(position).getImageUrl());
        this.startActivity(intent);
    }

    public void viewingNextMatch(View v){
        Intent intent = new Intent(TournamentMain.this,MatchActivity.class);

        Gson gson = new Gson();

        //Store anything in bundle here

        Game nextGame = tournament.getNextGame();

        if(nextGame != null){
            intent.putExtra("game", gson.toJson(nextGame));
            this.startActivityForResult(intent, 200);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "No more games!", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //matchActivity result
        if(requestCode == 200 && resultCode == 200){
            //handle return values
            tournament.getNextGame().setHomeTeamScore(data.getIntExtra("homeScore", -1));
            tournament.getNextGame().setAwayTeamScore(data.getIntExtra("awayScore", -1));
            tournament.getNextGame().setPlayed(true);
            tournament.updatePlayer();
            tournament.sortRankings();
            tournament.setRank();
            populatePlayers();
            System.out.println(tournament.getNextGame().getHomeTeam().getGamesPlayed());


            MainActivity.updateTournaments(getApplicationContext(), tournament);

            System.out.println(tournament.getNextGame().getHomeTeam().getGamesPlayed());
            tournament.incrementGameCounter();


            MainActivity.updateTournaments(getApplicationContext(), tournament);
        }

    }

    public void viewMatches(View v){
        Intent intent = new Intent(TournamentMain.this,MatchHistory.class);
        Gson gson = new Gson();
        String matchesSTR = gson.toJson(tournament.getPlayedGames());
        intent.putExtra("matches",matchesSTR);
        this.startActivity(intent);
    }

}
