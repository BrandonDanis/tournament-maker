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
        int[] rankings = new int[tournament.getPlayers().size()];

        for(int i=0; i< tournament.getPlayers().size(); i++){
            names[i] = tournament.getPlayers().get(i).getName();
            teams[i] = tournament.getPlayers().get(i).getTeamName();
            rankings[i] = tournament.getPlayers().get(i).getRanking();
        }

        MySimpleArrayAdapter arrayAdapter = new MySimpleArrayAdapter(getApplicationContext(), names, teams, rankings);
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
        this.startActivity(intent);

    }

}
