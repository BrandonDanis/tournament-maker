package four.elite.tournament;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 2015-12-03.
 */
public class DataManager {

    public static void saveTournaments(Context context,List<Tournament> tourneys){

        Gson gson = new Gson();
        FileOutputStream outputStream;

        String json = gson.toJson(tourneys);

        try {
            System.out.println("saving!");
            outputStream = context.openFileOutput("tournament-SRC", Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void formatFile(Context context){
        DataManager.saveTournaments(context, null);
    }

    public static List<Tournament> getTournaments(Context context){

        Gson gson = new Gson();
        String json = "";

        try {
            System.out.println("reading file!");
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(context.openFileInput("tournament-SRC")));
            String inputString;
            StringBuffer stringBuffer = new StringBuffer();
            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }

            json = stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Tournament> tournaments = gson.fromJson(json, new TypeToken<ArrayList<Tournament>>() {
        }.getType());

        return tournaments;
    }

    public static Tournament getTournamentByName(Context context, String name){
        List<Tournament> tournaments = DataManager.getTournaments(context);
        for(int i=0; i<tournaments.size(); i++){
            if(tournaments.get(i).getName().equals(name)){
                return tournaments.get(i);
            }
        }
        return null;
    }

    public static String getTeamsDatabaseJSONstring(Context context)
    {

        JSONObject jsonOBJ;
        String jsonSTR;
        BufferedReader br = null;

        try{
            br = new BufferedReader(new InputStreamReader(context.getAssets().open("TeamsDatabase.json")));
        }catch (IOException e){
            System.out.print("Fail");
        }

        // Reading json file from assets folder
        StringBuffer sb = new StringBuffer();
        try {
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        String myjsonstring = sb.toString();
        return myjsonstring;

    }

    public static String getTeamImageUrlWithName(Context context, String name){
        try{
            JSONArray jsonArr = new JSONArray(getTeamsDatabaseJSONstring(context));

            for(int i=0; i<jsonArr.length(); i++){
                if(jsonArr.getJSONObject(i).getString("name").equals(name)){
                    return jsonArr.getJSONObject(i).getString("imgPath");
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
