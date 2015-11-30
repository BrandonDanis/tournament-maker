package four.elite.tournament;

/**
 * Created by Brandon on 2015-11-29.
 */
public class Tournament {

    private Player[] players;

    public Tournament(Player[] players){
        this.players = players;
    }

    public Player[] getPlayer(){
        return this.players;
    }

}
