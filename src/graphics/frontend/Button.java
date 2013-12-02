package graphics.frontend;

import graphics.backend.Texture;
import util.PressAction;
import util.Rectangle;
import core.GameEngine;

public class Button extends BackgroundImage{
	Texture up, down, over;
	PressAction trigger;
	
	public Button(Rectangle rect, String imagePath, PressAction trigger) {
		this(rect, imagePath, imagePath, imagePath, trigger);
	}

	public Button(Rectangle rect, String upPath, String downPath, String overPath, PressAction trigger){
		super(rect, upPath);
		up = getTexture();
		down = new Texture(downPath);
		over = new Texture(overPath);
		
		this.trigger = trigger;
		GameEngine.buttons.add(this);
	}
	
	public void mouseDown(float x, float y){
		if(inBounds(x,y)){
			setTexture(down);
		}
	}
	
	public void mouseUp(float x, float y){
		if(inBounds(x,y) && getTexture() == down){
			trigger.fire();
			setTexture(up);
		}
	}
	
	public void mouseMove(float x, float y){
		if(inBounds(x,y) && getTexture() == up){
			setTexture(over);
		}
		else if(!inBounds(x,y) && getTexture() == over){
			setTexture(up);
		}
	}
	
	private float clampX(float x){
		x -= GameEngine.WIDTH/2.f;
		x /= GameEngine.WIDTH/2.f;
		return x;
	}
	
	private float clampY(float y){
		y -= GameEngine.HEIGHT/2.f;
		y /= GameEngine.HEIGHT/2.f;
		return y;
	}
	
	private boolean inBounds(float x, float y){
		return area.contains(clampX(x), clampY(y));
	}
}
