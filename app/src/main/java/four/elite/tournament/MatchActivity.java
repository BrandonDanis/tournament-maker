package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MatchActivity extends AppCompatActivity {

    NumberPicker homeTeamScore;
    NumberPicker awayTeamScore;

    TextView homeTeamLabel;
    TextView awayTeamLabel;
    TextView homePlayerName;
    TextView awayPlayerName;

    ImageView homeImage;
    ImageView awayImage;

    Game game;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        gson = new Gson();

        String json = bundle.getString("game");
        game = gson.fromJson(json, Game.class);

        //init all textviews and images
        homeTeamLabel = (TextView)findViewById(R.id.homeTeamLabel);
        awayTeamLabel = (TextView)findViewById(R.id.awayTeamLabel);
        homePlayerName = (TextView)findViewById(R.id.homePlayerLabel);
        awayPlayerName = (TextView)findViewById(R.id.awayPlayerLabel);

        homeTeamLabel.setText(game.getHomeTeam().getTeamName());
        awayTeamLabel.setText(game.getAwayTeam().getTeamName());
        homePlayerName.setText(game.getHomeTeam().getName());
        awayPlayerName.setText(game.getAwayTeam().getName());

        //init number pickers
        homeTeamScore = (NumberPicker)findViewById(R.id.homeTeamScore);
        homeTeamScore.setMaxValue(50);
        homeTeamScore.setMinValue(0);

        awayTeamScore = (NumberPicker)findViewById(R.id.awayTeamScore);
        awayTeamScore.setMaxValue(50);
        awayTeamScore.setMinValue(0);

        //init image loader
        ImageLoader imageLoader = ImageLoader.getInstance();
        homeImage = (ImageView)findViewById(R.id.homeTeamImage);
        awayImage = (ImageView)findViewById(R.id.awayTeamImage);
        imageLoader.displayImage(game.getHomeTeam().getImageUrl(),homeImage);
        imageLoader.displayImage(game.getHomeTeam().getImageUrl(),awayImage);

    }

    public void cancel(View v){
        finish();
    }

    public void confirmScore(View v){
        Intent intent = new Intent();

        game.setHomeTeamScore(homeTeamScore.getValue());
        game.setAwayTeamScore(awayTeamScore.getValue());

        intent.putExtra("game",gson.toJson(this.game));
        setResult(200, intent);
        finish();

    }


}
