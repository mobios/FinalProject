package coachingTools;

public class SoccerTeam extends Team {

	public SoccerTeam(String name) {
		super(name);
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++){
			players.add(new SoccerPlayer(i));
		}
	}
	
	public void makeFormation() {
		//not yet implemented
	}

}