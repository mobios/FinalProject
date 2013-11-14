package graphics;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Quad extends Model {

	@Override
	public void load(float[] verticies, byte[] indices) {		
		FloatBuffer verticiesBuffer = BufferUtils.createFloatBuffer(verticies.length);
		verticiesBuffer.put(verticies);
		verticiesBuffer.flip();
		
		ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		
		GL30.glBindVertexArray(vAO);
		
		int tempVBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, tempVBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticiesBuffer, GL15.GL_STATIC_DRAW);
		
	}

}
