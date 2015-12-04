package four.elite.tournament;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.MemoryHandler;

import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private List<Tournament> tournaments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tournaments = DataManager.getTournaments(getApplicationContext());

//        setupTournaments();
        populateListView();

    }

    //DEBUGGING METHOD!
    public void setupTournaments(){
        addTourney("Tournament 1");
        addTourney("Tournament 2");
        addTourney("Tournament 3");
        addTourney("Tournament 4");
        DataManager.saveTournaments(getApplicationContext(), tournaments);
    }

    //DEBUGGING METHOD!
    public void addTourney(String name){
        List<Player> players = new ArrayList<Player>();

        for(int i=0; i<4; i++){
            Player player = new Player("Team" + i,"Player" + i);
            players.add(player);
        }

        Tournament tourney = new Tournament("Tourney 1", players,"Round Robin");

        tournaments.add(tourney);
    }

    //DEBUGGING METHOD!
    public void getTourney(View v){
        List<Tournament> list = DataManager.getTournaments(getApplicationContext());
        System.out.println(list);
    }

    private void populateListView(){
        ListView listView = (ListView)findViewById(R.id.listView);
        List<String> tournamentNames = new ArrayList<String>();
        for(int i=0; i < tournaments.size(); i++){
            Tournament tournament = tournaments.get(i);
            System.out.println(tournament.getName());
            tournamentNames.add(tournament.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, tournamentNames
        );
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Populating!");
        Intent intent = new Intent(MainActivity.this,TournamentMain.class);
        intent.putExtra("Tournament Name", tournaments.get(position).getName());
        this.startActivity(intent);
    }


}
