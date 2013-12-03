package coachingTools;

import core.GameEngine;
import graphics.frontend.BallModel;
import graphics.frontend.PlayerModel;
import util.Point;
import util.Rectangle;


public class Player {

	private PlayerModel display;
	private int number, stamina, skill;
	public int scoredPoints;
	private boolean hasBall;
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, GOAL_KICK, NONE};
	public static enum FieldSide  {LEFT_HALF, RIGHT_HALF, HALF_LINE}
	private FieldSide fieldHalf;
	private SoccerArea region;
	private boolean goalie = false;
	private float[] teamColor;
	private float[] ballHolderColor;

	private BallModel ball;

	public Player(int number, int stamina, Point p, float[] tint) {
		super();

		this.number = number;
		this.stamina = stamina;
		scoredPoints = 0;
		display = new PlayerModel(p, tint);
		teamColor = tint;
		updateFieldArea();
	}

	public void scoreGoal() {
		scoredPoints++;
	}

	public void setGoalie(){
		goalie = true;
	}
	
	//used to set a players initial position
	public void setPosition(float x, float y) {

		display.setPosition(x, y);
		updateFieldArea();
	}

	//used to move a player from one place to another and update that players stamina
	public void move(float x, float y, int stamina){

		display.move(x, y);
		this.stamina -= stamina;
		updateFieldArea();
	}

	//used to pass the ball form one player to another.
	public void pass(Player player){
		if(hasBall){
			hasBall = false;
			display.setTint(teamColor); 
			player.display.setTint(ballHolderColor);
		}

	}

	// used to tell players where the important areas of the field are
	public void updateFieldArea(){

		// so the player knows which half of the field they are on
		if((this.getDisplay().getRect().getX() > -0.91f) && (this.getDisplay().getRect().getX() < -1.55f)){
			fieldHalf = FieldSide.LEFT_HALF;
		}else if((this.getDisplay().getRect().getX() < 0.61f) && (this.getDisplay().getRect().getX() > -1.45f)){
			fieldHalf = FieldSide.RIGHT_HALF;
		}else if((this.getDisplay().getRect().getX() <= -1.55f) && (this.getDisplay().getRect().getX() >= -1.45f)){
			fieldHalf = FieldSide.HALF_LINE;
		}
		// so the player knows what area they are in.
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

	public void setBall(BallModel arg1ball) {
		this.hasBall = arg1ball !=null;
		if(this.hasBall == true)
			display.setTint(ballHolderColor);
		else
			display.setTint(teamColor);
		this.ball = arg1ball;
	}

	public boolean isOffside(){
		return false;
	}	


	// ------ getters and setters ------ \\

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public boolean hasBall() {
		return hasBall;
	}

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

	public boolean isGoalie(){
		return goalie;
	}

	public PlayerModel getDisplay() {
		return display;
	}

	public void setDisplay(PlayerModel display) {
		this.display = display;
	}
	
	public float getYTop(){
		return display.getRect().getY()+display.getRect().getHeight()/2;
	}
	
	public float getYBottom(){
		return display.getRect().getY()-display.getRect().getHeight()/2;
	}
	
	public float getXLeft(){
		return display.getRect().getX()-display.getRect().getWidth()/2;
	}
	
	public float getXRight(){
		return display.getRect().getX()+display.getRect().getWidth()/2;
	}
}
