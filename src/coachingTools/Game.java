package coachingTools;

import java.util.ArrayList;

public class Game {
	private int period, speed = 100;
	protected Team team1, team2;
	protected Field gameField;
	
	public Game() {
		super();		
	}
	
	public void Formation(ArrayList<Player> players){
		
		
		
	}
	
	public void setDebugSeed(){
		
		
		
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

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Field getGameField() {
		return gameField;
	}
	
	public ArrayList<Player> getAllPlayers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		allPlayers.addAll(team1.getPlayers());
		allPlayers.addAll(team2.getPlayers());
		return allPlayers;		
	}
	
	public int getTeam1Score() {
		return team1.getScore();
	}
	
	public int getTeam2Score() {
		return team2.getScore();
	}
	
}
