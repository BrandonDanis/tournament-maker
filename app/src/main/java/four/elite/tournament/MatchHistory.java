package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MatchHistory extends AppCompatActivity {

    ListView matchList;
    List<Game> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);

        matchList = (ListView)findViewById(R.id.matchList);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Gson gson = new Gson();

        matches = gson.fromJson(bundle.getString("matches"), new TypeToken<ArrayList<Game>>(){}.getType());

        populateMatches();

    }

    private void populateMatches(){

        MatchLayoutArrayAdapter adapter = new MatchLayoutArrayAdapter(getApplicationContext(), matches);
        matchList.setAdapter(adapter);

    }



}
