package graphics.quadrangle;

import graphics.backend.renderCall;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import util.Rectangle;

public abstract class Quad implements renderCall{
	protected Rectangle area;
	protected static int vibo;
	
	protected final static int indexElementCount = Rectangle.elementCount();
	protected int buffluc;
	
	public abstract void move(float deltx, float delty);
	public abstract void remove(); // DO NOT USE// FORWARD IMPLEMENTATION ONLY
	
	public abstract int getStride();
	public abstract int getMax();
	
	public void setupVAO(){
		setVAO(GL30.glGenVertexArrays());
		GL30.glBindVertexArray(getVAO());
	}
	
	public void setupVBO(){
		setVBO(GL15.glGenBuffers());
	}
	
	public abstract void setVAO(int arg1);
	public abstract void setVBO(int arg1);
	public abstract int getVAO();
	public abstract int getVBO();
	
	public Quad(Rectangle area){
		this.area = area;
	}
	
	public void render(){
		setupBatch();
		bindTexture();
		renderLoop();
		teardownBatch();
		
	}
	
	public void setupVIBO(){
		ByteBuffer indexBuffer = BufferUtils.createByteBuffer(indexElementCount);
		indexBuffer.put(Rectangle.getOrder());
		indexBuffer.flip();
		
		vibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void bindVAO(){
		GL30.glBindVertexArray(getVAO());
		bindAttribs();
	}
	
	public void unbindVAO(){
		unbindAttribs();
		GL30.glBindVertexArray(0);
	}
	
	public void bindVBO(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getVBO());
	}
	
	public abstract void unbindVBO();
	public abstract void reloadVBO();
	
	public abstract void setupAttribs();
	public abstract void bindAttribs();
	public abstract void unbindAttribs();
	
	public static void bindVIBO(){
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vibo);
	}
	
	public static void unbindVIBO(){
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		
	}
	
	@Override
	public void setupBatch(){
		bindVAO();
		bindAttribs();
		bindVIBO();
	}
	
	@Override
	public void teardownBatch(){
		unbindVIBO();
		unbindAttribs();
		unbindVAO();
	}
}
