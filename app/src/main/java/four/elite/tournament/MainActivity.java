package four.elite.tournament;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private List<Tournament> tournaments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tournaments = DataManager.getTournaments(getApplicationContext());

        populateListView();

    }

    private void populateListView(){
        ListView listView = (ListView)findViewById(R.id.listView);
        List<String> tournamentNames = new ArrayList<String>();
        for(int i=0; i < tournaments.size(); i++){
            Tournament tournament = tournaments.get(i);
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
        Intent intent = new Intent(MainActivity.this,TournamentMain.class);
        intent.putExtra("Tournament Name", tournaments.get(position).getName());
        this.startActivity(intent);
    }


}
