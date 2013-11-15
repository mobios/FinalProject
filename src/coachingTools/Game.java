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
	
	// ------ getters and setters ------ \\
	
	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Player> getTeam1() {
		return team1;
	}

	public void setTeam1(ArrayList<Player> team1) {
		this.team1 = team1;
	}

	public ArrayList<Player> getTeam2() {
		return team2;
	}

	public void setTeam2(ArrayList<Player> team2) {
		this.team2 = team2;
	}

	public Field getGameField() {
		return gameField;
	}

	public static int getNumberOfPlayers() {
		return NUMBER_OF_PLAYERS;
	}
	
	
	
}
