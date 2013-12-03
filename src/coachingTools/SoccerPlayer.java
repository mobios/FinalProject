package coachingTools;

import util.Rectangle; 
import util.Point;

public class SoccerPlayer extends Player {
	
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, GOAL_KICK, NONE};
	public static enum FieldSide  {LEFT_HALF, RIGHT_HALF, HALF_LINE}
	private FieldSide fieldHalf;
	private SoccerArea region;
	private boolean goalie = false;
	
	public SoccerPlayer(int number, int stamina){
		super(number, stamina);
		updateFieldArea();
	}
	
	public SoccerPlayer(int number, int stamina, Point p, float[] tint) {
		super(number, stamina, p, tint);
	}
	
	public void scoreGoal() {
		scoredPoints++;
	}
	
	public void setGoalie(){
		goalie = true;
	}
	
	public void updateFieldArea(){
		
		// so the player knows which half of the field they are on
		if((this.getDisplay().getRect().getX() > -0.91f) && (this.getDisplay().getRect().getX() < -1.55f)){
			fieldHalf = FieldSide.LEFT_HALF;
		}else if((this.getDisplay().getRect().getX() < 0.61f) && (this.getDisplay().getRect().getX() > -1.45f)){
			fieldHalf = FieldSide.RIGHT_HALF;
		}else if((this.getDisplay().getRect().getX() <= -1.55f) && (this.getDisplay().getRect().getX() >= -1.45f)){
			fieldHalf = FieldSide.HALF_LINE;
		}
		// so the player knows what earea they are in.
		if((((this.getDisplay().getRect().getX() < -0.91f) && (this.getDisplay().getRect().getX() > -0.955f))&&
				((this.getDisplay().getRect().getY() < 0.165f)&&(this.getDisplay().getRect().getY() > -0.17f))) ||
				(((this.getDisplay().getRect().getX() < 0.655f) && (this.getDisplay().getRect().getX() > 0.61f))&&
				((this.getDisplay().getRect().getY() < 0.165f)&&(this.getDisplay().getRect().getY() > -0.17f)))){
			region = SoccerArea.GOAL;
		}else if((((this.getDisplay().getRect().getX() < -0.61f) && (this.getDisplay().getRect().getX() > -0.91f))&&
				((this.getDisplay().getRect().getY() < 0.915f)&&(this.getDisplay().getRect().getY() > -0.915f))) ||
				(((this.getDisplay().getRect().getX() < 0.61f) && (this.getDisplay().getRect().getX() > 0.31f))&&
				((this.getDisplay().getRect().getY() < 0.915f)&&(this.getDisplay().getRect().getY() > -0.915f))) ){
			region = SoccerArea.PENALTY_AREA;
		}else if((new Rectangle(-.86f, 0.0f, .1f, .63f).contains(this.getDisplay().getRect().getX(), this.getDisplay().getRect().getY())) ||
				(new Rectangle(.56f, 0.0f, .1f, .63f).contains(this.getDisplay().getRect().getX(), this.getDisplay().getRect().getY()))){
			region = SoccerArea.GOAL_AREA;
		}else if((new Rectangle(-.15f, 0.0f, .3f, .5f).contains(this.getDisplay().getRect().getX(), this.getDisplay().getRect().getY()))){
			region = SoccerArea.CENTER_CIRCLE;
		}else if((new Rectangle(-.86f, 0.0f, .1f, .63f).contains(this.getDisplay().getRect().getX(), this.getDisplay().getRect().getY())) ||
				(new Rectangle(-.58f, 0.0f, .05f, .4f).contains(this.getDisplay().getRect().getX(), this.getDisplay().getRect().getY()))){
			region = SoccerArea.PENALTY_ARC;
		}else{
			region = SoccerArea.NONE;
		}
		
	}
	
		public boolean isOffside(){
		return false;
	}
	
	public boolean isGoalie(){
		return goalie;
	}
	
	public void goalKick(){
		
	
	// ------ getters and setters ------ \\
	
	
	public SoccerArea getRegion() {
		return region;
	}

	public FieldSide getFieldHalf() {
		return fieldHalf;
	}

	
}


	
}
