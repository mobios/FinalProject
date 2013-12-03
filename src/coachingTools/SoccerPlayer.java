package coachingTools;

import util.Point;

public class SoccerPlayer extends Player {
	
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, GOAL_KICK};
	public static enum FieldSide  {LEFT_HALF, RIGHT_HALF}
	private FieldSide fieldHalf;
	private SoccerArea region;
	private boolean goalie = false;
	
	public SoccerPlayer(int number, int stamina, Point p, float[] tint) {
		super(number, stamina, p, tint);
	}
	
	public void scoreGoal() {
		scoredPoints++;
	}
	
	public void setGoalie(){
		goalie = true;
	}
	
	// ------ getters and setters ------ \\
	
	
	public SoccerArea getRegion() {
		return region;
	}

	public FieldSide getFieldHalf() {
		return fieldHalf;
	}

	public void setFieldHalf(FieldSide fieldHalf) {
		this.fieldHalf = fieldHalf;
	}

	public void setRegion(SoccerArea region) {
		this.region = region;
	}
	
	public boolean isOffside(){
		return false;
	}
	
	public boolean isGoalie(){
		return goalie;
	}
	
	public void goalKick(){
		
	}
	
}
