package coachingTools;

import java.util.ArrayList;

import util.Point;

public class Team {
	private ArrayList<Player> players;
	public String name;
	public float[] teamColor;
	public final static int NUMBER_OF_PLAYERS = 11;
	private FormationType formation;
	private FieldHalf fieldHalf;
	public enum FieldHalf {
		Left, Right;
	}
	public enum FormationType {
		FourFourTwo, FourThreeThree, ThreeFourThree, ThreeFiveTwo;
	}

	public Team(String name, float[] tint, FieldHalf half) {
		super();
		this.name = name;
		this.teamColor = tint;
		players = new ArrayList<Player>();
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++){

			players.add(new Player(i, 100, new Point(0, 0), tint));
			// set player 0 to the goalie
			if (i == 0){
				((Player)players.get(0)).setGoalie();
			}
		}
		
		this.fieldHalf = half;
	}
	
	// this constructor is used to make an empty static team that is only used to the store a team that is already in use
	public Team() {	
		
		
	}
	
	// to set players in the correct position depending on a enum FormationType
	public void getInFormation(FormationType f){
		formation = f;
		//j is used to place the players on the correct side of the field, and rightOffset offsets the players on the right side in the x direction
		float j = 1f;
		float rightOffset = 0f;
		if(fieldHalf == FieldHalf.Right){
			j = -1f;
			rightOffset = 0.3f;
		}
		((Player)players.get(0)).setPosition(j*(float) -0.9 - rightOffset, 0);
		
		for(int i = 1; i < players.size(); i++){
			if(isInFormation(FormationType.FourFourTwo)){
				if(i <= 4)
					((Player)players.get(i)).setPosition(j*-0.7f - rightOffset, (float)((i-2.5)*0.5));

				if(i >= 5 && i <= 8)
					((Player)players.get(i)).setPosition(j*-0.45f - rightOffset, (float)((i-6.5)*0.5));

				if(i >= 9)
					((Player)players.get(i)).setPosition(j*-0.25f - rightOffset, (float)((i-9.5)*0.6));
			}

			if(isInFormation(FormationType.FourThreeThree)){
				if(i <= 4)
					((Player)players.get(i)).setPosition(j*-0.7f - rightOffset, (float)((i-2.5)*0.5));

				if(i >= 5 && i <= 7)
					((Player)players.get(i)).setPosition(j*-0.45f - rightOffset, (float)((i-6)*0.6));

				if(i >= 8)
					((Player)players.get(i)).setPosition(j*-0.25f - rightOffset, (float)((i-9)*0.6));
			}
			
			
			if(isInFormation(FormationType.ThreeFourThree)){
				if(i <= 3)
					((Player)players.get(i)).setPosition(j*-0.7f - rightOffset, (float)((i-2)*0.6));

				if(i >= 4 && i <= 7)
					((Player)players.get(i)).setPosition(j*-0.45f - rightOffset, (float)((i-5.5)*0.5));

				if(i >= 8)
					((Player)players.get(i)).setPosition(j*-0.25f - rightOffset, (float)((i-9)*0.6));
			}
			
			if(isInFormation(FormationType.ThreeFiveTwo)){
				if(i <= 3)
					((Player)players.get(i)).setPosition(j*-0.7f - rightOffset, (float)((i-2)*0.6));

				if(i >= 4 && i <= 8)
					((Player)players.get(i)).setPosition(j*-0.45f - rightOffset, (float)((i-6)*0.4));

				if(i >= 9)
					((Player)players.get(i)).setPosition(j*-0.25f - rightOffset, (float)((i-9.5)*0.6));
			}
			
		}

	}
		public boolean isInFormation(FormationType f){
		if (f == formation){
			return true;
		}
		
		return false;
	}
		
		
	// ------ getters and setters ------ \\

	public ArrayList<Player> getPlayers() {
		return players;
	}
	

	
	public int getNumberOfPlayers() {
		return players.size();
	}
	
	public int getScore() {

		int teamScore = 0;
		for (Player p : players) {
			teamScore += p.scoredPoints;
		}
		
		return teamScore;
	}


}
