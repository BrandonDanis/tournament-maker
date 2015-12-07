package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TournamentResults extends AppCompatActivity {

    TextView playerName;
    TextView teamName;

    ImageView playerImage;

    Gson gson;

    Player player;

    ArrayList<Game> listOfPlayerMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        gson = new Gson();
        
        playerImage = (ImageView)findViewById(R.id.playerLogo);
        playerName = (TextView)findViewById(R.id.playerName);
        teamName = (TextView)findViewById(R.id.teamName);

        listOfPlayerMatches = gson.fromJson(bundle.getString("matches"), new TypeToken<ArrayList<Game>>(){}.getType());
        player = gson.fromJson(bundle.getString("winningPlayer"), Player.class);

        playerName.setText(player.getName());
        teamName.setText(player.getTeamName());

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(player.getImageUrl(), playerImage);


    }

    public void viewMatchHistory(View v){

        Intent intent = new Intent(TournamentResults.this, MatchHistory.class);

        intent.putExtra("matches",gson.toJson(listOfPlayerMatches));

        startActivity(intent);

    }

    public void leave(View v){
        finish();
    }

}
