package four.elite.tournament;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String teamName,playerName,imageUrl;

    private int ranking,gamesPlayed,gamesWon, totalGoalsFor, totalGoalsAgainst;

    private List <Game> games = new ArrayList<Game>();
    private List<Player> otherPlayers = new ArrayList<Player>();

    public Player(){}

    public Player(String teamName, String playerName, String url){
        this.teamName = teamName;
        this.playerName = playerName;
        this.imageUrl = url;
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

    public void addGames(Game game)
    {
        games.add(game);
    }
    public void addOtherPlayer(Player other)
    {
        otherPlayers.add(other);
    }


    public Game getGame(int index)
    {
        return games.get(index);
    }
    public int getRanking(){
        return this.ranking;
    }

    public boolean checkAlreadyPlaying(Player other)
    {
        for(int i = 0; i < otherPlayers.size(); i++)
        {
            if(other == otherPlayers.get(i))
            {
                return true;
            }
        }
        return false;
    }

    public Player getOtherPlayer(int index)
    {
        return otherPlayers.get(index);
    }

    public boolean alreadyPlayed (Player other)
    {
        for(int i = 0; i < otherPlayers.size(); i++)
        {
            if(otherPlayers.get(i).getTeamName() == other.getTeamName())
            {
                return true;
            }
        }
        return false;
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

    public void setGamesWon(int wins){
        this.gamesWon = wins;
    }

    public void setGamesPlayed(int games){
        this.gamesPlayed = games;
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
