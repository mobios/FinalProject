package coachingTools;

import java.util.ArrayList;

public class Team {
	private ArrayList<Player> players;
	public String name;
	
	public Team(String name) {
		super();
		this.name = name;
		players = new ArrayList<Player>();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void makeFormation() {
		//not yet implemented
	}
}
