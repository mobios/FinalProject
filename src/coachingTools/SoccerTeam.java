package coachingTools;

import util.Point;

public class SoccerTeam extends Team {
	public final static int NUMBER_OF_PLAYERS = 11;
	private FormationType formation;
	public enum FormationType {
		RUSH, DEFEND;
	}

	public SoccerTeam(String name, float[] tint) {
		super(name, tint);
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++){

			players.add(new SoccerPlayer(i, 100, new Point(((float)i)/15.0f,((float)i)/15.0f), tint));		
			if (i == 0){
				((SoccerPlayer)players.get(0)).setGoalie();
			}
		}
	}
	
	public void getInFormation(FormationType f){
		formation = f;
		
	}

	public boolean isInFormation(FormationType f){
		if (f == formation){
			return true;
		}
		
		return false;
	}
}
