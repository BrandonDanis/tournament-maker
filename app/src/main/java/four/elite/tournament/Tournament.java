package four.elite.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tournament{

    private List<Player> players = new ArrayList<Player>();
    private List<Game> games = new ArrayList<Game>();
    private int numberOfPlayers;
    private String name;
    private int maxGamesPerPlayer;
    private final int maxNumberofPlayers = 16;
    private String tournamentFormat;

    public Tournament(String name, List<Player> players, String tournamentFormat) {
        this.players = players;
        this.tournamentFormat = tournamentFormat;
        this.numberOfPlayers = players.size();
        maxGamesPerPlayer = players.size() - 1;
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
            homeOrAway = (int)(Math.random()*(2));
            current = new Game();
            for(int j = 0; j < players.size(); j++)
            {
                if(i != j)
                {
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
                    players.get(i).addGames(current);
                    players.get(j).addGames(current);
                    games.add(current);
                }
            }
        }
    }
    public void sortRankings()
    {
        Player temp;
        for(int i = 0; i < numberOfPlayers; i++)
        {
            for(int j = i; j < numberOfPlayers; j++)
            {
                if(players.get(i).getRanking() > players.get(j).getRanking())
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
            players.get(i).addGames(current);
            players.get(numPlayers - i - 1).addGames(current);
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

    public Game getGame(int index)
    {
        return games.get(index);
    }


}
