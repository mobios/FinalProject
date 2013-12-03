package graphics.frontend;

import graphics.backend.Texture;
import util.Clamp;
import util.MouseEvent;
import util.PressAction;
import util.Rectangle;
import core.GameEngine;

public class Button extends GuiElement{
	Texture up, down, over, disable;
	PressAction trigger;
	
	private boolean active = true;
	private boolean sticky = false;
	
	public Button(Rectangle rect, String imagePath, PressAction trigger) {
		this(rect, imagePath, imagePath, imagePath, trigger);
	}

	public Button(Rectangle rect, String upPath, String downPath, String overPath, PressAction trigger){
		this(rect, upPath, downPath, overPath, upPath, trigger);
	}
	
	public Button(Rectangle rect, String upPath, String downPath, String overPath, String disablePath, PressAction trigger){
		super(rect, upPath);
		up = getTexture();
		down = new Texture(downPath);
		over = new Texture(overPath);
		disable = new Texture(disablePath);
		
		this.trigger = trigger;
		GameEngine.buttons.add(this);
	}

	public void setSticky(boolean arg){
		sticky = arg;
	}
	
	public boolean mouseDown(float x, float y){
		if(inBounds(x,y)){
			if(!sticky)setTexture(down);
			return true;
		}
		return false;
	}
	
	public boolean mouseUp(float x, float y){
		if(getTexture() == down || (sticky && inBounds(x,y))){
			if(inBounds(x,y) && active)
				trigger.fire();
			
			if(active)setTexture((sticky) ? ((getTexture() == down) ? up : down) : up);
			return true;
		}
		return false;
	}
	
	public boolean mouseMove(float x, float y){
		if(!inBounds(x,y)){
			if(!sticky)setTexture(up);
			return false;
		}
		
		if(inBounds(x,y) && getTexture() == up){
			if(!sticky)setTexture(over);
			return true;
		}
		
		return false;
	}
	
	public boolean handleMouse(MouseEvent event, float x, float y){
		if(!active){
			return false;
		}
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
	
	private boolean inBounds(float x, float y){
		return area.contains(Clamp.clampX(x), Clamp.clampY(y));
	}
	
	public void toggle(){
		if(active){
			active = false;
			setTexture(disable);
		}
		
		else{
			active = true;
			setTexture(up);
		}
	}
}
