package coachingTools;

import java.util.ArrayList;

public class SoccerGame extends Game {

	public SoccerGame() {
		super();

		gameField = new SoccerField();
		team1 = new SoccerTeam("BestTeamEver");
		team2 = new SoccerTeam("BesterTeamEver");
	}
	
	public void throwIn(ArrayList<Player> players){
		
		
	}

	@Override
	public Team getTeam1() {
		return (SoccerTeam) team1;
	}
	
	@Override
	public Team getTeam2() {
		return (SoccerTeam) team2;
	}
	
	public void goalKick(){
		
	}
	
	public void kickOff(Player p){
		
	}
	
}
