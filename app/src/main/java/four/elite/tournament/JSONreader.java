package four.elite.tournament;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONreader {

    public static String getJSONstring(BufferedReader br)
    {
        List<String> questions = new ArrayList();
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

    public static JSONObject getTournamentAtIndex(String jsonSTR, int index){
        try{
            JSONObject jsonOBJ = new JSONObject(jsonSTR);
            JSONArray jsonArr = jsonOBJ.getJSONArray("tournaments");
            if(jsonArr.length() >= index){
                return jsonArr.getJSONObject(index);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getTournamentByName(String jsonSTR, String name){
        try{
            JSONObject jsonOBJ = new JSONObject(jsonSTR);
            JSONArray jsonArr = jsonOBJ.getJSONArray("tournaments");
            for(int i=0; i<jsonArr.length(); i++){
                if(jsonArr.getJSONObject(i).getString("name").equals(name)){
                    return jsonArr.getJSONObject(i);
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getPlayerAtIndex(String jsonSTR, String tournamentName, int index){
        try{
            JSONObject jsonOBJ = new JSONObject(jsonSTR);
            JSONArray tourneyArr = jsonOBJ.getJSONArray("tournaments");
            for(int i=0; i<tourneyArr.length(); i++){
                if(tourneyArr.getJSONObject(i).getString("name").equals(tournamentName)){
                    JSONArray playerArr = tourneyArr.getJSONObject(i).getJSONArray("players");
                    if(playerArr.length() >= index){
                        return playerArr.getJSONObject(index);
                    }
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getPlayerByName(String jsonSTR, String tournamentName, String name){
        try{
            JSONObject jsonOBJ = new JSONObject(jsonSTR);
            JSONArray tourneyArr = jsonOBJ.getJSONArray("tournaments");
            for(int i=0; i<tourneyArr.length(); i++){
                if(tourneyArr.getJSONObject(i).getString("name").equals(name)){
                    JSONArray playerArr = tourneyArr.getJSONObject(i).getJSONArray("players");
                    for(int y=0; i<playerArr.length(); y++){
                        if(playerArr.getJSONObject(i).getString("name").equals(name)){
                            return playerArr.getJSONObject(i);
                        }
                    }
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
