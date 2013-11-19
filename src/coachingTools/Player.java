package coachingTools;

public abstract class Player extends Image {
	private float x, y;
	private int number, stamina, skill;
	protected int scoredPoints;
	private boolean hasBall;
	
	public Player(int number, int stamina) {
		super();
		this.number = number;
		this.stamina = stamina;
		scoredPoints = 0;
	}

	public void move(float x, float y, int stamina){
		this.x = x;
		this.y = y;
		
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
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
