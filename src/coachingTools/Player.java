package coachingTools;

import graphics.frontend.PlayerModel;
import util.Point;


public class Player {
	
	private PlayerModel display;
	private int number, stamina, skill;
	public int scoredPoints;
	private boolean hasBall;
	private int width, height;
	public static enum SoccerArea {GOAL, PENALTY_AREA, PENALTY_ARC, GOAL_AREA, CENTER_CIRCLE, GOAL_KICK};
	public static enum FieldSide  {LEFT_HALF, RIGHT_HALF}
	private FieldSide fieldHalf;
	private SoccerArea region;
	private boolean goalie = false;
	private float[] teamColor;
	private float[] ballHolderColor;
	
	
	public Player(int number, int stamina, Point p, float[] tint) {
		super();
		
		this.number = number;
		this.stamina = stamina;
		scoredPoints = 0;
		ballHolderColor = new float[] {1.0f, 1.0f, 1.0f, 1.0f};
		display = new PlayerModel(p, tint);
		teamColor = tint;
	}
	
	public void scoreGoal() {
		scoredPoints++;
	}
	
	public void setGoalie(){
		goalie = true;
	}
	
	public void setPosition(float x, float y) {
		display.setPosition(x, y);
	}

	public void move(float x, float y, int stamina){
		
		display.move(x, y);
		this.stamina -= stamina;
		
	}
	
	public void pass(Player player){
		if(hasBall){
			hasBall = false;
			display.setTint(teamColor); 
			player.setBall(true);
			player.display.setTint(ballHolderColor);
		}
		
	}
	
	
	public void loadImage(String img){
		
		
		
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

	public void setBall(boolean hasBall) {
		this.hasBall = hasBall;
		if(this.hasBall == true)
			display.setTint(ballHolderColor);
		else
			display.setTint(teamColor);
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
	
	public boolean isOffside(){
		return false;
	}
	
	public boolean isGoalie(){
		return goalie;
	}
	
	public void goalKick(){
		
	}

	public PlayerModel getDisplay() {
		return display;
	}

	public void setDisplay(PlayerModel display) {
		this.display = display;
	}
	
	
	
}
