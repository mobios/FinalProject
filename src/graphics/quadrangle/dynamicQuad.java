package graphics.quadrangle;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import util.Rectangle;

public class dynamicQuad extends Quad {	
	private Rectangle area;
	
	private static int vbo;
	private static int vao;
	
	private static int count=0;
	private static int max=0;
	
	public dynamicQuad(Rectangle rect){
		super();
		if(rect == null)
			return;
		area = rect;
		buffluc = count;
		count++;
		reloadVBO();
	}
	

	public void setup(){
		setupVAO();
	}

	@Override
	public void setupVAO(){
		if(max > 0)
			return;
		
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(getStride() * core.Size.quadVertices * 1);
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_DYNAMIC_DRAW);
		GL20.glVertexAttribPointer(0, core.Size.pvs, GL11.GL_FLOAT, false, getStride(), 0);
		GL20.glVertexAttribPointer(1, core.Size.mvs, GL11.GL_FLOAT, false, getStride(), core.Size.pvs);
		GL20.glVertexAttribPointer(2, core.Size.tvs, GL11.GL_FLOAT, false, getStride(), core.Size.pvs + core.Size.mvs);
	
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		setupVIBO();
		GL30.glBindVertexArray(0);
		max = 50;
	}

	@Override
	public void reloadVBO(){
		float[][] vertices = area.get3dWithUV();
		
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length*(core.Size.pvs + core.Size.mvs + core.Size.tvs));
		for(float[] vec5 : vertices){
			verticesBuffer.put(vec5);
			verticesBuffer.put(new float[]{0.4f,0.4f,0.23f,0.58f});
		}
		
		verticesBuffer.flip();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		int debugValueForVariableWatch = getStride();
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, getStride()*buffluc, verticesBuffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public void close() {
		GL20.glDisableVertexAttribArray(0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vbo);
		
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vao);
	}

	@Override
	public void move(float deltx, float delty) {
		area.move(deltx, delty);
		
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
	public void bindVAO() {
		GL30.glBindVertexArray(vao);
	}

	@Override
	public void unbindVAO() {
		GL30.glBindVertexArray(0);
	}

	@Override
	public void bindVBO() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
	}
	@Override
	public void unbindVBO() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
}
