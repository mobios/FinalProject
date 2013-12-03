package coachingTools;

import util.Point;

public class SoccerTeam extends Team {
	public final static int NUMBER_OF_PLAYERS = 11;
	private FormationType formation;
	public enum FormationType {
		FourFourTwo, FourThreeThree, ThreeFourThree, ThreeFiveTwo;
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
		((SoccerPlayer)players.get(0)).setPosition((float) -0.9, 0);

		for(int i = 1; i < players.size(); i++){
			if(isInFormation(FormationType.FourFourTwo)){
				if(i <= 4)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.7, (float)((i-2.5)*0.5));

				if(i >= 5 && i <= 8)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.45, (float)((i-6.5)*0.5));

				if(i >= 9)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.25, (float)((i-9.5)*0.6));
			}

			if(isInFormation(FormationType.FourThreeThree)){
				if(i <= 4)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.7, (float)((i-2.5)*0.5));

				if(i >= 5 && i <= 7)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.45, (float)((i-6)*0.6));

				if(i >= 8)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.25, (float)((i-9)*0.6));
			}
			
			
			if(isInFormation(FormationType.ThreeFourThree)){
				if(i <= 3)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.7, (float)((i-2)*0.6));

				if(i >= 4 && i <= 7)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.45, (float)((i-5.5)*0.5));

				if(i >= 8)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.25, (float)((i-9)*0.6));
			}
			
			if(isInFormation(FormationType.ThreeFiveTwo)){
				if(i <= 3)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.7, (float)((i-2)*0.6));

				if(i >= 4 && i <= 8)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.45, (float)((i-6)*0.4));

				if(i >= 9)
					((SoccerPlayer)players.get(i)).setPosition((float) -0.25, (float)((i-9.5)*0.6));
			}
			
		}

	}

	public boolean isInFormation(FormationType f){
		if (f == formation){
			return true;
		}
		
		return false;
	}
}
