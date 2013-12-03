package graphics.frontend;

import util.Rectangle;

public class Placeable {
	public static GuiElement elm;
	public static final float x =.1f;
	public static final float y =.1f;
	public static final float w =.1f;
	public static final float h =.1f;
	
	public static boolean placeable = false;
	
	public static void setup(){
		elm = new GuiElement(new Rectangle(x,y,w,h), "resources/region.png");
		elm.setTint(new float[]{1f,1f,1f,.5f});
	}
	
	public static void toggle(){
		
	}
	
	public static void isPlaceable(){
		
	}
	
	public static void inBounds(float x, float y){
		
	}
}
