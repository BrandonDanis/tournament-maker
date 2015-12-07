package four.elite.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament{

    private List<Player> players = new ArrayList<Player>();
    private List<Game> games = new ArrayList<Game>();
    private boolean completed = false;
    private int gameCounter;
    private int numberOfPlayers;
    private String name;
    private String tournamentFormat;

    public Tournament(String name, List<Player> players, String tournamentFormat) {
        this.players = players;
        this.tournamentFormat = tournamentFormat;
        this.numberOfPlayers = players.size();
        this.name = name;
        this.gameCounter = 0;

        if(tournamentFormat.equals("Round Robin")){
            setRoundRobinGames();
        }else if(tournamentFormat.equals("Knockout")){
            setKnockOut();
        }else{
            setRoundRobinGames();
        }

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public void setRoundRobinGames()
    {
        Game current;
        int homeOrAway;
        for(int i = 0; i < players.size(); i++)
        {
            for(int j = i; j < players.size(); j++)
            {
                homeOrAway = (int)(Math.random()*(2));
                if(i != j)
                {
                    current = new Game();
                    if(homeOrAway == 0)
                    {
                        current.setHomeTeam(players.get(i));
                        current.setAwayTeam(players.get(j));
                    }
                    else
                    {
                        current.setHomeTeam(players.get(j));
                        current.setAwayTeam(players.get(i));
                    }

                    games.add(current);
                }
            }

        }
    }
    public void setRank()
    {
        for(int i = 0 ; i < players.size();i++)
        {
            players.get(i).setRanking(i+1);
        }
    }
    public void sortRankings(){
        Player temp;
        for(int i = 0; i < numberOfPlayers; i++)
        {
            for(int j = 0; j < numberOfPlayers; j++)
            {
                if(i != j){
                    if(players.get(i).getGamesWon() < players.get(j).getGamesWon())
                    {
                        temp = players.get(i);
                        players.set(i, players.get(j));
                        players.set(j, temp);
                    }
                    else if (players.get(i).getRanking() == players.get(j).getRanking())
                    {
                        if(players.get(i).getTotalGoalsFor() > players.get(j).getTotalGoalsFor())
                        {
                            temp = players.get(i);
                            players.set(i, players.get(j));
                            players.set(j, temp);
                        }
                        else if(players.get(i).getTotalGoalsFor() == players.get(j).getTotalGoalsFor())
                        {
                            if(players.get(i).getTotalGoalsAgainst() < players.get(j).getTotalGoalsAgainst())
                            {
                                temp = players.get(i);
                                players.set(i, players.get(j));
                                players.set(j, temp);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setKnockOut()
    {
        setupKnockout(numberOfPlayers);
    }

    public void setKnockout(int numPlayers)
    {
        this.sortRankings();
        setupKnockout(numPlayers);
    }

    private void setupKnockout(int numPlayers)
    {
        Game current;
        for(int i = 0; i < numberOfPlayers/2 ; i++)
        {
            current = new Game();
            current.setHomeTeam(players.get(i));
            current.setAwayTeam(players.get(numPlayers - i - 1));
            games.add(current);
        }
    }

    public Player getPlayer(int index)
    {
        return players.get(index);
    }

    public void advanceNextRound()
    {
        List<Game> lastRound = new ArrayList<Game>();
        for(int i = 0 ; i < games.size(); i++)
        {
            lastRound.add(games.get(i));
        }
        games.clear();
        for(int i = 0; i < lastRound.size()/2; i++) {
            Game current = lastRound.get(i);
            Player advanceOne = current.getWinningPlayer();
            current = lastRound.get(lastRound.size() - 1 - i);
            Player advanceTwo = current.getWinningPlayer();

            current = new Game();
            current.setHomeTeam(advanceOne);
            current.setAwayTeam(advanceTwo);

            games.add(current);
        }
    }

    public List<Game> getPlayedGames(){
        List<Game> gamesPlayed = new ArrayList<Game>();
        for(int i=0; i<games.size(); i++){
            if(games.get(i).getPlayed()){
                gamesPlayed.add(games.get(i));
            }
        }
        return gamesPlayed;
    }

    public Game getGame(int index)
    {
        return games.get(index);
    }

    public boolean tournamentFinished()
    {
        for(int i = 0; i < games.size(); i++)
        {
            if(!(games.get(i).getPlayed()))
            {
                return false;
            }
        }
        return true;
    }

    public int numberOfGames()
    {
        return games.size();
    }

    public void setGamePlayed(int index)
    {
        games.get(index).setPlayed(true);
    }

    public Game getNextGame()
    {
        if(gameCounter < games.size()) {
            Game next = games.get(gameCounter);
            return next;
        }
        return null;
    }

    public String getType()
    {
        return tournamentFormat;
    }

    public List<Game> getPlayerMatches (Player current)
    {
        List <Game> thisPlayersGame = new ArrayList<Game>();
        for(int i = 0 ; i < games.size(); i++)
        {
            if(games.get(i).getHomeTeam().getName().equals(current.getName()) || games.get(i).getAwayTeam().getName().equals(current.getName()))
            {
                thisPlayersGame.add(games.get(i));
            }
        }
        return thisPlayersGame;
    }

    public void updatePlayer() {
        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).getName().equals(games.get(gameCounter).getHomeTeam().getName()) || players.get(i).getName().equals(games.get(gameCounter).getAwayTeam().getName()))
            {
                players.get(i).incrementGamesPlayed();
                if(players.get(i).getName().equals(games.get(gameCounter).getHomeTeam().getName()))
                {
                    players.get(i).addGoalsFor(games.get(gameCounter).getHomeTeamScore());
                    players.get(i).addGoalsAgainst(games.get(gameCounter).getAwayTeamScore());
                }
                if(players.get(i).getName().equals(games.get(gameCounter).getAwayTeam().getName()))
                {
                    players.get(i).addGoalsFor(games.get(gameCounter).getAwayTeamScore());
                    players.get(i).addGoalsAgainst(games.get(gameCounter).getHomeTeamScore());
                }
            }
            if(players.get(i).getName().equals(games.get(gameCounter).getWinningPlayer().getName()))
            {
                players.get(i).incrementGamesWon();
            }
        }

    }

    public boolean isCompleted(){
        return completed;
    }
    public void Complete(boolean bool){
        completed = bool;
    }

    public int getGameCounter()
    {
        return gameCounter;
    }
    public void incrementGameCounter()
    {
        gameCounter++;
    }
}
