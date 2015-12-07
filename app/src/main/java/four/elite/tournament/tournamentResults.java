package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TournamentResults extends AppCompatActivity {

    TextView playerName;
    TextView teamName;

    ImageView playerImage;

    Gson gson;

    List<Game> listOfPlayerMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        gson = new Gson();

        playerImage = (ImageView)findViewById(R.id.imageView);
        playerName = (TextView)findViewById(R.id.playerName);
        teamName = (TextView)findViewById(R.id.teamName);

        ImageLoader imageLoader = ImageLoader.getInstance();
        //imageLoader.displayImage(imageURL, playerImage);


        //INIT listOfPlayerMatches


        //init text fields
        //playerName.setText();
        //teamName.setText();


    }

    public void viewMatchHistory(View v){

        Intent intent = new Intent(TournamentResults.this, MatchActivity.class);

        intent.putExtra("matches",gson.toJson(listOfPlayerMatches));

        startActivity(intent);

    }

    public void leave(View v){
        finish();
    }

}
