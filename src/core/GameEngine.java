package core;

import graphics.frontend.Button; 

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import util.MouseEvent;

public class GameEngine {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	public static List<Button> buttons;
	
	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File("native").getAbsolutePath());
		setup();
		RenderEngine.test();
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
		
		GL11.glClearColor(0f, 0f, 0f, 0f);
	}
	
	public static void setup(){
		OpenGL3();
		//GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_FASTEST);
		RenderEngine.setup();
		buttons = new ArrayList<Button>();
	}
	
	public static void run(){
		int error = GL11.glGetError();
		if(error > 0)
			return;
		while(!Display.isCloseRequested()){
			handleMouse();
			RenderEngine.render();
			Display.sync(60);
			Display.update();
		}
		
		Display.destroy();
		
	}
	
	public static void handleMouse(){
		MouseEvent event;
		
		int mx = Mouse.getEventX();
		int my = Mouse.getEventY();
		
		while(Mouse.next()){
			if(Mouse.getEventButton() == 0){
				if(Mouse.isButtonDown(0))
					event = MouseEvent.DOWN;
				else
					event = MouseEvent.UP;
			}
			else{
				event = MouseEvent.MOVE;
			}
			for(Button button : buttons)
				if(button.handleMouse(event, mx, my))
					break;
			
//			if(Mouse.getEventButton() == 0){
//				if(Mouse.isButtonDown(0)){
//					for(Button button : buttons){
//						button.mouseDown(Mouse.getEventX(), Mouse.getEventY());
//					}
//					return;
//				}
//
//				for(Button button : buttons){
//					button.mouseUp(Mouse.getEventX(), Mouse.getEventY());
//				}
//			}
//			else{
//				for(Button button : buttons){
//					button.mouseMove(Mouse.getEventX(), Mouse.getEventY());
//				}
//			}
		}
	}
}
