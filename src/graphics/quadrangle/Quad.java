package graphics.quadrangle;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import util.Rectangle;

public abstract class Quad {
	protected Rectangle area;
	protected static int vibo;
	
	protected final static int indexElementCount = Rectangle.elementCount();
	protected int buffluc;
	
	public static void setup(){
		ByteBuffer indexBuffer = BufferUtils.createByteBuffer(indexElementCount);
		indexBuffer.put(Rectangle.getOrder());
		indexBuffer.flip();
		
		vibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		
		dynamicQuad.setup();
		StaticQuad.setup();
		UniformQuad.setup();
	}
	
	public static int getVbo() {
		return 0;
	}
	
	public static int getVao() {
		return 0;
	}
	
	public static int getVibo() {
		return 0;
	}
	
	public static void render() {
		
	}
	
	public abstract void move(float deltx, float delty);
	public abstract void remove(); // DO NOT USE// FORWARD IMPLEMENTATION ONLY
}
