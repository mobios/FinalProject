package graphics.backend.segmentDisplay;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import graphics.backend.Texture;
import graphics.frontend.GuiElement;
import graphics.quadrangle.Dynamic;
import util.Point;
import util.Rectangle;

public abstract class Segment extends GuiElement{
	private static float[] tintOn = new float[] {1.0f, 1.0f, 1.0f, 1.0f};
	private static float[] tintOff = new float[] {0.0f, 0.0f, 0.0f, 0.0f};
	protected static final float SCALE = 0.06f;
	
	public Segment(Rectangle rect, String path){
		super(rect, path);
	}
	
	public Segment(){
		super();
	}
	
	public void turnOn(){
		setTint(tintOn);
	}
	
	public void turnOff(){
		setTint(tintOff);
	}
}
