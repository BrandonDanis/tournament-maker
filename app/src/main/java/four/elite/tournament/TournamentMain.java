package four.elite.tournament;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class TournamentMain extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    public Tournament tournament;

    TextView tourneyNameLabel;
    ListView listView;

    Button nextMatch;

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

        if(tournament.isCompleted()){
            nextMatch = (Button)findViewById(R.id.nextMatch);
            nextMatch.setText("Results");
        }

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
            if(tournament.getType().equals("Round Robin") || tournament.getType().equals("Knockout")){
                tournament.complete(true);

                Intent tourneyResultIntent = new Intent(TournamentMain.this, TournamentResults.class);
                tourneyResultIntent.putExtra("winningPlayer", gson.toJson(tournament.getPlayer(0)));
                tourneyResultIntent.putExtra("matches", gson.toJson(tournament.getPlayedGames()));
                startActivity(tourneyResultIntent);
            }
            else{
                final AlertDialog numDlg;
                final NumberPicker numPkr;

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                numPkr = new NumberPicker(dialogBuilder.getContext());

                numPkr.setMinValue(0);
                numPkr.setMaxValue(tournament.getPlayers().size());

                dialogBuilder.setTitle("Player in Knockout Stage");
                dialogBuilder.setView(numPkr);
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialogBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int numProceeding = numPkr.getValue();

                        tournament.sortRankings();

                        while(tournament.getPlayers().size() > numProceeding){
                            tournament.getPlayers().remove(tournament.getPlayers().size() - 1);
                        }
                        tournament = new Tournament(tournament.getName(), tournament.getPlayers(), "Knockout");

                        MainActivity.updateTournaments(getApplicationContext(), tournament);

                        populatePlayers();
                    }
                });
                numDlg = dialogBuilder.create();
                numDlg.show();

            }


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
