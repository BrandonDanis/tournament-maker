package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TournamentMain extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    public static Tournament tournament;

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

        //Store anything in bundle here

        this.startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //matchActivity result
        if(requestCode == 200 && resultCode == 200){
            //handle return values
            int homeScore = data.getIntExtra("homeScore",0); //if homeScore is null, it will set to 0
            int awayScore = data.getIntExtra("awayScore",0); //if awayScore is null, it will set to 0

            //Handle finishing match

        }

    }
}
