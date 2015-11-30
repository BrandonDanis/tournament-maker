package four.elite.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String jsonSTR = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPage();
    }

    public void initPage(){
        JSONObject jsonOBJ;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("data.json")));
            jsonSTR = JSONreader.getJSONstring(br);
        }catch (IOException e){
            System.out.print("Fail");
        }

        try{
            jsonOBJ = new JSONObject(jsonSTR);
            JSONArray jsonArr = jsonOBJ.getJSONArray("tournaments");
            if(jsonArr.length() != 0){
                populateListView(jsonArr);
            }else{
                System.out.println("No current tournament!");
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void populateListView(JSONArray jsonArray){

        ListView listView = (ListView)findViewById(R.id.listView);

        try{

            List<String> values = new ArrayList<String>();

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                values.add(item.getString("name"));
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, values
            );

            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(this);

        }catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Fail");
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        JSONObject tournament = null;
        String teamName = null;

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("data.json")));
            jsonSTR = JSONreader.getJSONstring(br);
            tournament = JSONreader.getTournamentAtIndex(jsonSTR,position);
        }catch (IOException e){
            System.out.print("Fail");
        }

        try {
            teamName = tournament.getString("name");
        }catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(MainActivity.this,TournamentMain.class);
        intent.putExtra("Tournament Name", teamName);
        this.startActivity(intent);

    }


}
