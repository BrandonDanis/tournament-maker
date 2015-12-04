package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerCreation extends AppCompatActivity {

    TextView playerNameLabel;
    TextView teamNameLabel;
    ImageView teamImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_creation);

        playerNameLabel = (TextView)findViewById(R.id.playerNameLabel);
        teamNameLabel = (TextView)findViewById(R.id.teamNameLabel);
        teamImage = (ImageView)findViewById(R.id.teamImage);

    }

    public void cancelButtonClicked(View v){
        finish();
    }

    public void tryToAddUser(View v){

        if(playerNameLabel.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Player name not valid", Toast.LENGTH_LONG);
            toast.show();
        }else if(teamNameLabel.getText().toString().trim().equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Team name not valid", Toast.LENGTH_LONG);
            toast.show();
        }else{

            Intent intent = new Intent();

            intent.putExtra("Player Name",playerNameLabel.getText().toString());
            intent.putExtra("Team Name",teamNameLabel.getText().toString());

            setResult(200, intent);
            finish();

        }

    }

}
