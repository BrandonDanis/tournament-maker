package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //init all textviews and images
        homeTeamLabel = (TextView)findViewById(R.id.homeTeamLabel);
        awayTeamLabel = (TextView)findViewById(R.id.awayTeamLabel);
        homePlayerName = (TextView)findViewById(R.id.homePlayerLabel);
        awayPlayerName = (TextView)findViewById(R.id.awayPlayerLabel);

//        homeTeamLabel.setText(bundle.getString("Home Team Name"));
//        awayTeamLabel.setText(bundle.getString("Away Team Name"));
//        homePlayerName.setText(bundle.getString("Home Player Name"));
//        awayPlayerName.setText(bundle.getString("Away Player Name"));

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
//        imageLoader.displayImage(bundle.getString("Away URL"),homeImage);
//        imageLoader.displayImage(bundle.getString("Home URL"),awayImage);

    }

    public void cancel(View v){
        finish();
    }

    public void confirmScore(View v){
        Intent intent = new Intent();
        intent.putExtra("homeScore", homeTeamScore.getValue());
        intent.putExtra("awayScore", awayTeamScore.getValue());

        setResult(200, intent);
        finish();

    }


}
