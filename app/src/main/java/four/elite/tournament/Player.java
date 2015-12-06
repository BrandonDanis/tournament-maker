package four.elite.tournament;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String teamName,playerName,imageUrl;

    private int ranking,gamesPlayed, gamesWon, totalGoalsFor, totalGoalsAgainst;

    public Player(){}

    public Player(String teamName, String playerName, String url){
        this.teamName = teamName;
        this.playerName = playerName;
        this.imageUrl = url;
        this.gamesWon = 0;
        this.gamesPlayed = 0;
        if(url == null){
            this.imageUrl = "http://dogr.io/doge.png";
        }

    }

    public String getName(){
        return this.playerName;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }

    public void setImageUrl(String url){
        this.imageUrl = url;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public int getGamesPlayed(){
        return this.gamesPlayed;
    }

    public int getGamesWon(){
        return this.gamesWon;
    }

    public int getRanking(){
        return this.ranking;
    }

    public void setName(String name){
        this.playerName = name;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public void setRanking(int rank){
        this.ranking = rank;
    }

    public void incrementGamesWon(){
        this.gamesWon++;
    }

    public void incrementGamesPlayed(){
        this.gamesPlayed++;
    }

    public void addGoalsFor(int goals)
    {
        totalGoalsFor += goals;
    }
    public void addGoalsAgainst(int goalsAgainst)
    {
        totalGoalsAgainst += goalsAgainst;
    }

    public int getTotalGoalsFor()
    {
        return totalGoalsFor;
    }

    public int getTotalGoalsAgainst()
    {
        return totalGoalsAgainst;
    }

}
