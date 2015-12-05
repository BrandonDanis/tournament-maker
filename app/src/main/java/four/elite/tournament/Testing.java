package four.elite.tournament;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 2015-12-03.
 */
public class Testing {

    public static void setupFakeTournaments(Context context){

        List<Tournament> tournaments = new ArrayList<Tournament>();

        List<Player> laLigaPlayers = new ArrayList<Player>();
        Player messi = new Player("Barcelona","Lionel Messi", null);
        Player ronaldo = new Player("Real Madrid","Cristiano Ronaldo", null);
        laLigaPlayers.add(messi);
        laLigaPlayers.add(ronaldo);
        Tournament laLiga = new Tournament("La Liga", laLigaPlayers, "Round Robin");

        DataManager.saveTournaments(context, tournaments);

    }

}
