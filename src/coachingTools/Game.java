package coachingTools;

import java.util.ArrayList;

public class Game {
	private final static int NUMBER_OF_PLAYERS = 11;
	private int period, speed = 100;
	private ArrayList<Player> team1, team2;
	private Field gameField;
	
	public Game() {
		super();
		gameField = new SoccerField();
		
		team1 = new ArrayList<Player>();
		team2 = new ArrayList<Player>();
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++){
			team1.add(new SoccerPlayer(i+1));
			team2.add(new SoccerPlayer(i+1));
		}
		
	}
	
	public void Formation(ArrayList<Player> players){
		
		
		
	}
	
}
