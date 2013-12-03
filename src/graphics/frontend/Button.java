package graphics.frontend;

import graphics.backend.Texture;
import util.MouseEvent;
import util.PressAction;
import util.Rectangle;
import core.GameEngine;

public class Button extends GuiElement{
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
	
	public boolean mouseDown(float x, float y){
		if(inBounds(x,y)){
			setTexture(down);
			return true;
		}
		return false;
	}
	
	public boolean mouseUp(float x, float y){
		if(getTexture() == down){
			if(inBounds(x,y))
				trigger.fire();
			
			setTexture(up);
			return true;
		}
		return false;
	}
	
	public boolean mouseMove(float x, float y){
		if(!inBounds(x,y)){
			setTexture(up);
			return true;
		}
		
		if(inBounds(x,y) && getTexture() == up){
			setTexture(over);
			return true;
		}
		
		return false;
	}
	
	public boolean handleMouse(MouseEvent event, float x, float y){
		switch(event){
		case UP:
			return mouseUp(x,y);
			
		case DOWN:
			return mouseDown(x,y);
			
		case MOVE:
			return mouseMove(x, y);
		}
		return false;
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
