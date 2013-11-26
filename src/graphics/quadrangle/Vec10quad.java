package graphics.quadrangle;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import util.Rectangle;

public abstract class Vec10quad extends Quad{
	private float[] tint;
	
	public Vec10quad(Rectangle rect, float[] tint){
		super(rect);
		if(rect == null)
			return;
		
		this.tint = tint;
		reloadVBO();
	}
	
	@Override
	public void setupVAO(){		
		super.setupVAO();
		bindVAO();
		setupVBO();
		
		setupAttribs();
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		setupVIBO();
	}
	
	@Override
	public void setupAttribs(){
		GL20.glVertexAttribPointer(0, core.Size.pvs, GL11.GL_FLOAT, false, getStride()*4, 0);
		GL20.glVertexAttribPointer(1, core.Size.mvs, GL11.GL_FLOAT, false, getStride()*4, core.Size.pvs*4);
		GL20.glVertexAttribPointer(2, core.Size.tvs, GL11.GL_FLOAT, false, getStride()*4, (core.Size.pvs + core.Size.mvs)*4);
	}
	
	@Override
	public void reloadVBO(){
		float[][] vertices = area.get3dWithUV();
		
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length*(core.Size.pvs + core.Size.mvs + core.Size.tvs));
		for(float[] vec6 : vertices){
			verticesBuffer.put(vec6);
			verticesBuffer.put((tint == null || tint.length != 4) ? new float[]{0.f,0.f,0.f,0.f} : tint);
		}
		
		verticesBuffer.flip();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getVBO());
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, getStride()*buffluc, verticesBuffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getStride() {
		return core.Size.pvs + core.Size.mvs + core.Size.tvs;
	}

	@Override
	public void renderLoop() {
		GL11.glDrawElements(GL11.GL_TRIANGLES, indexElementCount*getMax(), GL11.GL_UNSIGNED_BYTE, 0);
		
	}
	
	@Override
	public void bindAttribs(){
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
	}
	
	@Override
	public void unbindAttribs(){
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
	}
}
