package four.elite.tournament;

public class Game {

    private Player homeTeam = null;
    private Player awayTeam = null;

    private int homeScore = -1;
    private int awayScore = -1;

    private Boolean played = false;

    public Game() {

    }

    public Player getHomeTeam(){
        return this.homeTeam;
    }

    public Player getAwayTeam(){
        return this.awayTeam;
    }

    public Boolean getPlayed(){
        return this.played;
    }

    public void setHomeTeam(Player home){
        this.homeTeam = home;
    }

    public void setAwayTeam(Player away){
        this.awayTeam = away;
    }

    public void setPlayed(Boolean bool){
        this.played = bool;
    }

    public Player getWinningPlayer(){


        if(homeScore > awayScore){
            return homeTeam;
        }else if(awayScore > homeScore){
            return awayTeam;
        }
        else {
            return null;
        }
    }
    
    public void setHomeTeamScore(int score)
    {
    	this.homeScore = score;
    }
    public void setAwayTeamScore(int score)
    {
    	this.awayScore = score;
    }
    public int getHomeTeamScore()
    {
        return homeScore;
    }

    public int getAwayTeamScore()
    {
        return awayScore;
    }


}
