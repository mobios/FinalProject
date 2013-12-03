package graphics.quadrangle;

import graphics.backend.renderCall;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import util.Rectangle;

public abstract class Quad implements renderCall{
	protected Rectangle area;
	
	protected final static int indexElementCount = Rectangle.elementCount();
	protected int buffluc;
	
	public abstract void remove(); // DO NOT USE// FORWARD IMPLEMENTATION ONLY
	
	public abstract int getStride();
	public abstract int getMax();
	public abstract int getNumElements();
	public abstract int getNumObjects();
	
	public void setupVAO(){
		setVAO(GL30.glGenVertexArrays());
	}
	
	public void setupVBO(){
		setVBO(GL15.glGenBuffers());
	}
	
	public abstract void setVAO(int arg1);
	public abstract void setVBO(int arg1);
	public abstract void setVIBO(int arg1);
	
	public abstract int getVAO();
	public abstract int getVBO();
	public abstract int getVIBO();
	
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
		ByteBuffer indexBuffer = BufferUtils.createByteBuffer(indexElementCount*getNumObjects());
		for(int i = 0; i < getNumObjects(); ++i){
			indexBuffer.put(Rectangle.getOrder(4*i));
		}
		indexBuffer.flip();
		setVIBO(GL15.glGenBuffers());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, getVIBO());
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL15.GL_DYNAMIC_DRAW);
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
	
	public void unbindVBO(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	public abstract void reloadVBO();
	
	public abstract void setupAttribs();
	public abstract void bindAttribs();
	public abstract void unbindAttribs();
	
	public void bindVIBO(){
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, getVIBO());
	}
	
	public void unbindVIBO(){
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
	
	public void move(float deltx, float delty) {
		area.move(deltx, delty);
		reloadVBO();
	}
	
	public void setPosition(float x, float y){
		area.setX(x);
		area.setY(y);
		reloadVBO();
	}
	
	public abstract int getLuc();
	
	public Rectangle getRect(){
		return area;
	}
}
