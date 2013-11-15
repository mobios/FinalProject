package coachingTools;

import java.util.ArrayList;

public class Game {
	private int period, speed = 100;
	private Team team1, team2;
	private Field gameField;
	
	public Game() {
		super();
		gameField = new SoccerField();
		
		team1 = new Team("BestTeamEver");
		team2 = new Team("BesterTeamEver");		
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
	
}
