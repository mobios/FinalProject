package core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class GameEngine {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static void main(String[] args) {
		setup();
		run();
	}
		
	public static void OpenGL3(){
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(3,2)
		.withProfileCore(true).withForwardCompatible(true);
		
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("FIFA World Cup 2020 -- Quatar Edition");
			Display.create(pixelFormat, contextAtrributes);
			
			GL11.glViewport(0, 0, WIDTH, HEIGHT);
		} catch(LWJGLException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		GL11.glClearColor(0.4f, 0.6f, 0.9f, 0);
	}
	
	public static void setup(){
		OpenGL3();
		//GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
		RenderEngine.setup();
	}
	
	public static void run(){
		while(!Display.isCloseRequested()){
			RenderEngine.render();
			Display.sync(120);
			Display.update();
		}
		
		Display.destroy();
		
	}
}
