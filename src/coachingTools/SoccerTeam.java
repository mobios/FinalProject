package coachingTools;

public class SoccerTeam extends Team {

	public SoccerTeam(String name) {
		super(name);
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++){
			players.add(new SoccerPlayer(i+1));
		}
	}
	
	@Override
	public void makeFormation() {
		//not yet implemented
	}

}
