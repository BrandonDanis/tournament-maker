package four.elite.tournament;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private static List<Tournament> tournaments = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tournaments = DataManager.getTournaments(getApplicationContext());

        listView = (ListView)findViewById(R.id.listView);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                promptTournamentRemoval(position);
                return true;
            }

        });


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
        TournamentLayoutArrayAdapter arrayAdapter = new TournamentLayoutArrayAdapter(getApplicationContext(),tournaments);
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
        if(requestCode == 200 && resultCode == 200){
            String jsonSTR = data.getStringExtra("tournament-object");
            Tournament tournamentToAdd = gson.fromJson(jsonSTR, Tournament.class);

            tournaments.add(tournamentToAdd);
            DataManager.saveTournaments(this, tournaments);
            populateListView();
        }

    }

    private void promptTournamentRemoval(int position){
        final int pos = position;
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to remove this tournament?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        tournaments.remove(pos);
                        populateListView();
                        DataManager.saveTournaments(getApplicationContext(),tournaments);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public static void updateTournaments(Context context,Tournament tournamentToUpdate){

        for(int i = 0 ; i < tournaments.size(); i++){
            if(tournaments.get(i).getName().equals(tournamentToUpdate.getName()))
            {
                tournaments.set(i, tournamentToUpdate);
                DataManager.saveTournaments(context,tournaments);
                return;
            }
        }
    }


}
