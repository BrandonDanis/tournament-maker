package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainCreate extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView tournamentName;
    ListView playersListView;

    Tournament newTournament;
    List<Player> players = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);

        tournamentName = (TextView)findViewById(R.id.tournamentName);
        playersListView = (ListView)findViewById(R.id.playerListView);


        populatePlayers();

    }

    public void addingNewPlayer(View v){
        Intent intent=new Intent(MainCreate.this,PlayerCreation.class);
        startActivityForResult(intent, 200);
    }

    public void populatePlayers(){

        String[] names = new String[players.size()];
        String[] teams = new String[players.size()];
        int[] rankings = new int[players.size()];

        for(int i=0; i< players.size(); i++){
            names[i] = players.get(i).getName();
            teams[i] = players.get(i).getTeamName();
        }

        MySimpleArrayAdapter arrayAdapter = new MySimpleArrayAdapter(getApplicationContext(), names, teams, rankings);
        playersListView.setAdapter(arrayAdapter);
        playersListView.setOnItemClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 200 && resultCode == 200){

            String playerName = data.getStringExtra("Player Name");
            String teamName = data.getStringExtra("Team Name");

            Player playerToAdd = new Player(teamName, playerName);

            players.add(playerToAdd);

            populatePlayers();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
