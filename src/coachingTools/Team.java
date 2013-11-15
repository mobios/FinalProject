package coachingTools;

import java.util.ArrayList;

public abstract class Team {
	protected final static int NUMBER_OF_PLAYERS = 11;
	protected ArrayList<Player> players;
	public String name;
	
	public Team(String name) {
		super();
		this.name = name;
		players = new ArrayList<Player>();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public static int getNumberOfPlayers() {
		return NUMBER_OF_PLAYERS;
	}
	
	public abstract void makeFormation();
}
