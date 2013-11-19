package core;

import org.lwjgl.opengl.GL11;

import util.Origin;
import util.Rectangle;
import graphics.quadrangle.Quad;
import graphics.quadrangle.dynamicQuad;

public class RenderEngine {
	public static Quad quads;
	public static dynamicQuad dquads;
	
	public static void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		dquads.render();
	}
	
	public static void setup(){
		dquads = new dynamicQuad(null);
		dquads.setup();
		
	}
	
	public static void test(){
		new dynamicQuad(new Rectangle(0,0, 1, 1, Origin.CENTER));
	}
	
	public static void compileShaders(){
		
	}
}
