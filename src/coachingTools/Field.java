package coachingTools;

import util.Rectangle;
import graphics.frontend.BackgroundImage;

public class Field extends BackgroundImage {
	public static final float x = -.15f;
	public static final float y = .0f;
	public static final float width = 1.7f;
	public static final float height = 1.5f;
	
	public static final float top = y+height/2;
	public static final float bottom = y-height/2;
	public static final float left = x-width/2;
	public static final float right = x+width/2;
	
	
	
	
	public Field() {
		//creates a rectangle for the backgroundImage and imposes a .png image on it to create the field
		super(new Rectangle(x,y,width,height), "resources/field.png");
	}
	
}
