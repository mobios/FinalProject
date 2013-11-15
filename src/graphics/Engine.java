package graphics;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import util.Origin;
import util.Rectangle;

public class Engine {
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	
	Collection<Model> mesh;
	public static void main(String[] args) {
		new Engine();

	}
	
	public Engine(){
		mesh = new ArrayList<Model>();
		OpenGL3();
		testQuad();
		
		while(!Display.isCloseRequested()){
			render();
			Display.sync(120);
			Display.update();
		}
		
		Display.destroy();
	}
	
	public void OpenGL3(){
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
	
	public void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		for(Model object : mesh)
			object.render();
	}
	
	public void testQuad(){
		float[] vertices = {
			-0.25f, 0.25f, 0.f,
			-0.25f, -0.25f, 0.f,
			0.25f, -0.25f, 0.f,
			0.25f, 0.25f, 0.f
		};
		
		byte[] indices={0,1,2,2,3,0};
		
		mesh.add(new dynamicQuad(new Rectangle(0,0,1.f,1.f, Origin.CENTER)));
	}
}
