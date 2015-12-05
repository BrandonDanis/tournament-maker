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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private List<Tournament> tournaments = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tournaments = DataManager.getTournaments(getApplicationContext());

        listView = (ListView)findViewById(R.id.listView);

        if(tournaments != null)
            populateListView();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);

    }

    private void populateListView(){
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

    public void initNewTourneySetup(View v){
        Intent intent = new Intent(MainActivity.this,MainCreate.class);
        this.startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Gson gson = new Gson();

        //returned from MainCreate with valid data
        if(requestCode == 200 && requestCode == 200){
            String jsonSTR = data.getStringExtra("tournament-object");
            Tournament tournamentToAdd = gson.fromJson(jsonSTR, Tournament.class);

            tournaments.add(tournamentToAdd);
            DataManager.saveTournaments(this, tournaments);
            populateListView();
        }

    }

}
