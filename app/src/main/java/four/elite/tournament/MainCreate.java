package four.elite.tournament;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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
        String[] imageUrls = new String[players.size()];
        int[] rankings = new int[players.size()];


        for(int i=0; i< players.size(); i++){
            names[i] = players.get(i).getName();
            teams[i] = players.get(i).getTeamName();
            imageUrls[i] = players.get(i).getImageUrl();
        }

        MySimpleArrayAdapter arrayAdapter = new MySimpleArrayAdapter(getApplicationContext(), names, teams, rankings, imageUrls);
        playersListView.setAdapter(arrayAdapter);
        playersListView.setOnItemClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 200 && resultCode == 200){

            String playerName = data.getStringExtra("Player Name");
            String teamName = data.getStringExtra("Team Name");
            String imageUrl = data.getStringExtra("Image URL");


            Player playerToAdd = new Player(teamName, playerName, imageUrl);

            players.add(playerToAdd);

            populatePlayers();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to remove user?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        players.remove(position);
                        populatePlayers();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    public void finishCreation(View v){

        if(players.size() < 2){
            Toast toast = Toast.makeText(getApplicationContext(), "Need at least 2 players", Toast.LENGTH_LONG);
            toast.show();
        }else if (tournamentName.getText().toString().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Not a valid Tournament name", Toast.LENGTH_LONG);
            toast.show();
        }else{

            AlertDialog levelDialog;

            final CharSequence[] options = {"Round Robin","Knockout","Round Robin & Knockout"};

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Select type of tournament!");
            dialog.setSingleChoiceItems(options, -1, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    //Setup intent
                    final Intent intent = new Intent();
                    Tournament newTournament;

                    switch (item) {
                        case 0:
                            newTournament = new Tournament(tournamentName.getText().toString(),players,options[0].toString());
                            intent.putExtra("tournament-object", new Gson().toJson(newTournament));
                            setResult(200, intent);
                            finish();
                            break;
                        case 1:
                            newTournament = new Tournament(tournamentName.getText().toString(),players,options[1].toString());
                            intent.putExtra("tournament-object", new Gson().toJson(newTournament));
                            setResult(200, intent);
                            finish();
                            break;
                        case 2:
                            newTournament = new Tournament(tournamentName.getText().toString(),players,options[2].toString());
                            intent.putExtra("tournament-object", new Gson().toJson(newTournament));
                            setResult(200, intent);
                            finish();
                            break;
                    }
                }
            });

            dialog.setNegativeButton("Cancel", null);
            levelDialog = dialog.create();
            levelDialog.show();

        }

    }

}
