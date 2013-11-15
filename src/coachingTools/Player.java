package coachingTools;

public class Player extends Image {
	
	private int number, stamina, skill;
	private boolean ball;
	
	public Player(int number) {
		super();
		this.number = number;
	}

	public void move(float x, float y, int stamina){
		
		
		
	}
	
	public void pass(Player palyer){
		
		
		
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

	public boolean isBall() {
		return ball;
	}

	public void setBall(boolean ball) {
		this.ball = ball;
	}
	
	public void throwInBall() {
		
	}
	
	
	
}
