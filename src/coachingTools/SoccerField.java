package coachingTools;

import util.Rectangle;
import graphics.frontend.BackgroundImage;


public class SoccerField extends BackgroundImage {
	
	public SoccerField() {
		super();
		new BackgroundImage(new Rectangle(-.15f, .0f, 1.7f, 2.0f), "resources/field.png");
	}
	
	
}
