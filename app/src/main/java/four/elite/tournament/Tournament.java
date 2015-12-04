package four.elite.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tournament implements Serializable{

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
        int teamOne, homeOrAway, teamTwo;
        for(int i = 0; i < (players.size() - 1)*(players.size() - 1); i++)
        {
            teamOne = (int)(Math.random()*((players.size())));
            teamTwo = (int)(Math.random()*((players.size())));
            current = new Game();
            homeOrAway = (int)(Math.random()*(2));
            if(players.get(teamOne).alreadyPlayed(players.get(teamTwo))&&
                    players.get(teamOne).totalGames() != maxGamesPerPlayer &&
                    players.get(teamTwo).totalGames() != maxGamesPerPlayer && teamOne != teamTwo) {
                Player playerOne = players.get(teamOne);
                Player playerTwo = players.get(teamTwo);
                if (homeOrAway == 0) {
                    current.setHomeTeam(playerOne);
                    current.setAwayTeam(playerTwo);
                } else {
                    current.setAwayTeam(playerOne);
                    current.setHomeTeam(playerTwo);
                }
                playerOne.incrementTotalGames();
                playerOne.addOtherPlayer(playerTwo);
                playerOne.addGames(current);
                playerTwo.incrementTotalGames();
                playerTwo.addOtherPlayer(playerOne);
                playerTwo.addGames(current);
                this.games.add(current);
            }
            else
            {
                i--;
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
            current = lastRound.get(games.size() - i);
            Player advanceTwo = current.getWinningPlayer();

            current = new Game();
            current.setHomeTeam(advanceOne);
            current.setAwayTeam(advanceTwo);

            games.add(current);
        }
    }

}
