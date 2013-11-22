package coachingTools;

import java.util.ArrayList;

public abstract class Team {
	protected ArrayList<Player> players;
	public String name;
	public float[] teamColor;
	
	public Team(String name) {
		super();
		this.name = name;
		players = new ArrayList<Player>();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	
	public int getNumberOfPlayers() {
		return players.size();
	}
	
	public int getScore() {

		int teamScore = 0;
		for (Player p : players) {
			teamScore += p.scoredPoints;
		}
		
		return teamScore;
	}
}
