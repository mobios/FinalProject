package graphics.frontend;

import core.GameEngine;
import util.Rectangle;

public class Button extends BackgroundImage {
	public Button(Rectangle rect, String imagePath) {
		super(rect, imagePath);
		GameEngine.buttons.add(this);
	}

	public void clickMade(float x, float y){
		x -= GameEngine.WIDTH/2.f;
		y -= GameEngine.HEIGHT/2.f;
		
		x /= GameEngine.WIDTH/2.f;
		y /= GameEngine.HEIGHT/2.f;
		
		if(area.contains(x, y)){
			System.exit(0);
		}
	}
}
