package four.elite.tournament;

public class Player {

    private String teamName,playerName;
    private int ranking,gamesPlayed,gamesWon;

    public Player(){}

    public Player(String teamName, String playerName){
        this.teamName = teamName;
        this.playerName = playerName;
    }

    public String getName(){
        return this.playerName;
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

    public void setGamesWon(int wins){
        this.gamesWon = wins;
    }

    public void setGamesPlayed(int games){
        this.gamesPlayed = games;
    }

}
