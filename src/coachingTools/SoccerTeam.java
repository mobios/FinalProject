package coachingTools;

public class SoccerTeam extends Team {
	public enum FormationType {
		RUSH, DEFEND;
	}

	public SoccerTeam(String name) {
		super(name);
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++){
			players.add(new SoccerPlayer(i));
		}
	}
	
	public void getInFormation(FormationType f){
		
	}

	public boolean isInFormation(FormationType f){
		return false;
	}
}
