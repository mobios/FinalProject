package graphics;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Quad extends Model {
	private int indexBufferObject;
	private int indexCount;
	private float x, y;
	private float height, width;
	
	public Quad(float[] verticies, byte[] indices){
		super();
		indexBufferObject = GL15.glGenBuffers();
		load(verticies,indices);
	}
	
	@Override
	public void load(float[] verticies, byte[] indices) {		
		FloatBuffer verticiesBuffer = BufferUtils.createFloatBuffer(verticies.length);
		verticiesBuffer.put(verticies);
		verticiesBuffer.flip();
		
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		indexCount = indices.length;
		
		GL30.glBindVertexArray(vAO);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticiesBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0,3, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBufferObject);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	@Override
	public void render() {		
		GL30.glBindVertexArray(vAO);
		GL20.glEnableVertexAttribArray(0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBufferObject);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, indexCount, GL11.GL_UNSIGNED_BYTE, 0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void close() {
		GL20.glDisableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(indexBufferObject);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vBO);
		
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vAO);
	}
}
