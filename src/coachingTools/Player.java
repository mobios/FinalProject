package coachingTools;

import util.Point;
import graphics.frontend.PlayerModel;

public abstract class Player {
	private PlayerModel display;
	private int number, stamina, skill;
	protected int scoredPoints;
	private boolean hasBall;
	private int width, height;
	
	public Player(int number, int stamina){
		super();
		
		this.number = number;
		this.stamina = stamina;
		scoredPoints = 0;
	}
	
	public Player(int number, int stamina, Point p, float[] tint){
		super();
		
		this.number = number;
		this.stamina = stamina;
		scoredPoints = 0;
		//display = new PlayerModel(p, tint);
	}

	public void move(float x, float y, int stamina){
		
		display.move(x, y);
		this.stamina -= stamina;
		
	}
	
	public void pass(Player player){
		if(hasBall){
			hasBall = false;
			player.setBall(true);
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
	}
	
	public void throwInBall() {
		
	}
}
