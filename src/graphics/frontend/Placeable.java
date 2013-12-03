package graphics.frontend;

import coachingTools.Team;
import util.Rectangle;

public class Placeable {
	public static GuiElement elm;
	public static Button control;
	
	public static final float x =.1f;
	public static final float y =.1f;
	public static final float w =.1f;
	public static final float h =.1f;
	
	public static boolean placeable = false;
	
	public static void setup(){
		elm = new GuiElement(new Rectangle(x,y,w,h), "resources/region.png");
		control = new Button(new Rectangle(.85f, -.05f, .25f, .13f), "resources/rplayer/up.png", "resources/rplayer/down.png", "resources/rplayer/hover.png", "resources/rplayer/disabled.png", (new util.PressAction(){public void fire(){toggle();};}));
	}
	
	public static void toggle(){
		control.toggle();
		if(placeable){
			placeable = false;
			elm.setTint(new float[]{0,0,0,0});
			return;
		}
		
		placeable = true;
		elm.setTint(new float[]{1f,1f,1f,.5f});
	}
	
	public static boolean isPlaceable(){
		return placeable;
	}
	
	public static boolean inBounds(float x, float y){
		return elm.getRect().contains(x, y);
	}
}
