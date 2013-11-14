package graphics;

import java.nio.FloatBuffer;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Engine {
	public static void main(String[] args) {
		new Engine();

	}
	
	public Engine(){
		OpenGL3();
		
		while(!Display.isCloseRequested()){
			Display.sync(120);
			Display.update();
		}
		
		Display.destroy();
	}
	
	public void OpenGL3(){
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAtrributes = new ContextAttribs(4,0)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setTitle("FIFA World Cup 2020 -- Quatar Edition");
			Display.create(pixelFormat, contextAtrributes);
		} catch(LWJGLException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void testDraw(){
		float[] verticies = {
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		
		FloatBuffer verticiesBuffer = BufferUtils.createFloatBuffer(verticies.length);
		verticiesBuffer.put(verticies);
		verticiesBuffer.flip();
		
		byte[] indices = {
				0, 1, 2,
				2, 3, 0;
		};
		
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indices.length);
	}
}
