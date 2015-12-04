package four.elite.tournament;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

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

        List<Tournament> tournaments = gson.fromJson(json, List.class);

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

}
