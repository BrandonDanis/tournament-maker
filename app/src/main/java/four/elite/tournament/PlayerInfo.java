package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerInfo extends AppCompatActivity {

    String tournamentName;
    String playerName;
    String imageURL;

    TextView playerNameLabel;
    TextView teamNameLabel;
    TextView rankLabel;
    TextView gamesPlayedLabel;
    TextView gamesWonLabel;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //init textViews
        playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);
        teamNameLabel = (TextView)findViewById(R.id.teamNameLabel);
        rankLabel = (TextView)findViewById(R.id.rankLabel);
        gamesPlayedLabel = (TextView)findViewById(R.id.gamesPlayedLabel);
        gamesWonLabel = (TextView)findViewById(R.id.gamesWonLabel);
        imageView = (ImageView)findViewById(R.id.imageView);

        //init variables
        tournamentName = bundle.getString("Tournament Name");
        playerName = bundle.getString("Player Name");
        imageURL = bundle.getString("Image URL");

        //fill textviews with values
        playerNameLabel.setText(bundle.getString("Player Name"));
        teamNameLabel.setText(bundle.getString("Player Team"));
        rankLabel.append(Integer.toString(bundle.getInt("Player Ranking")));
        gamesPlayedLabel.append(Integer.toString(bundle.getInt("Player Games")));
        gamesWonLabel.append(Integer.toString(bundle.getInt("Player Wins")));

        //init image loader
        ImageLoader imageLoader = ImageLoader.getInstance();
        System.out.print(imageURL);
        imageLoader.displayImage(imageURL, imageView);



    }



}
