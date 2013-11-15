package coachingTools;

public class SoccerPlayer extends Player {
	
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, LEFT_HALF, RIGHT_HALF}
	private SoccerArea region;
	
	public SoccerPlayer(int number) {
		super(number);
		
	}
	
	// ------ getters and setters ------ \\
	
	public SoccerArea getRegion() {
		return region;
	}

	public void setRegion(SoccerArea region) {
		this.region = region;
	}
	
}
