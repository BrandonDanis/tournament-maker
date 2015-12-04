package four.elite.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 2015-11-29.
 */
public class Tournament implements Serializable{

    private List<Player> players = new ArrayList<Player>();
    private List<Game> games = new ArrayList<Game>();

    private String name;

    public Tournament(String name, List<Player> players){
        this.name = name;
        this.players = players;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
