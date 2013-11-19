package coachingTools;

public class SoccerPlayer extends Player {
	
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE};
	public static enum FieldSide  {LEFT_HALF, RIGHT_HALF}
	private FieldSide fieldHalf;
	private SoccerArea region;
	
	public SoccerPlayer(int number) {
		super(number);
	}
	
	public void scoreGoal() {
		scoredPoints++;
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
		return true;
	}
	
	public void goalKick(){
		
	}
	
}
