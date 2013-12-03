package coachingTools;

import util.Rectangle;

public class SoccerPlayer extends Player {
	
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, GOAL_KICK, NONE};
	public static enum FieldSide  {LEFT_HALF, RIGHT_HALF, HALF_LINE}
	private FieldSide fieldHalf;
	private SoccerArea region;
	private boolean goalie = false;
	
	public SoccerPlayer(int number, int stamina) {
		super(number, stamina);
		updateFieldArea();
	}
	
	public void scoreGoal() {
		scoredPoints++;
	}
	
	public void setGoalie(){
		goalie = true;
	}
	
	public void updateFieldArea(){
		
		// so the player knows which half of the field they are on
		if((this.getMesh().getRect().getX() > -0.91f) && (this.getMesh().getRect().getX() < -1.55f)){
			fieldHalf = FieldSide.LEFT_HALF;
		}else if((this.getMesh().getRect().getX() < 0.61f) && (this.getMesh().getRect().getX() > -1.45f)){
			fieldHalf = FieldSide.RIGHT_HALF;
		}else if((this.getMesh().getRect().getX() <= -1.55f) && (this.getMesh().getRect().getX() >= -1.45f)){
			fieldHalf = FieldSide.HALF_LINE;
		}
		// so the player knows what earea they are in.
		if((((this.getMesh().getRect().getX() < -0.91f) && (this.getMesh().getRect().getX() > -0.955f))&&
				((this.getMesh().getRect().getY() < 0.165f)&&(this.getMesh().getRect().getY() > -0.17f))) ||
				(((this.getMesh().getRect().getX() < 0.655f) && (this.getMesh().getRect().getX() > 0.61f))&&
				((this.getMesh().getRect().getY() < 0.165f)&&(this.getMesh().getRect().getY() > -0.17f)))){
			region = SoccerArea.GOAL;
		}else if((((this.getMesh().getRect().getX() < -0.61f) && (this.getMesh().getRect().getX() > -0.91f))&&
				((this.getMesh().getRect().getY() < 0.915f)&&(this.getMesh().getRect().getY() > -0.915f))) ||
				(((this.getMesh().getRect().getX() < 0.61f) && (this.getMesh().getRect().getX() > 0.31f))&&
				((this.getMesh().getRect().getY() < 0.915f)&&(this.getMesh().getRect().getY() > -0.915f))) ){
			region = SoccerArea.PENALTY_AREA;
		}else if((new Rectangle(-.86f, 0.0f, .1f, .63f).contains(this.getMesh().getRect().getX(), this.getMesh().getRect().getY())) ||
				(new Rectangle(.56f, 0.0f, .1f, .63f).contains(this.getMesh().getRect().getX(), this.getMesh().getRect().getY()))){
			region = SoccerArea.GOAL_AREA;
		}else if((new Rectangle(-.15f, 0.0f, .3f, .5f).contains(this.getMesh().getRect().getX(), this.getMesh().getRect().getY()))){
			region = SoccerArea.CENTER_CIRCLE;
		}else if((new Rectangle(-.86f, 0.0f, .1f, .63f).contains(this.getMesh().getRect().getX(), this.getMesh().getRect().getY())) ||
				(new Rectangle(-.58f, 0.0f, .05f, .4f).contains(this.getMesh().getRect().getX(), this.getMesh().getRect().getY()))){
			region = SoccerArea.PENALTY_ARC;
		}else{
			region = SoccerArea.NONE;
		}
		
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
