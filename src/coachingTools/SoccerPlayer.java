package coachingTools;

public class SoccerPlayer extends Player {
	
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, LEFT_HALF, RIGHT_HALF}
	private SoccerArea region;
	
	public SoccerPlayer() {
		super();
		
	}
	
}
