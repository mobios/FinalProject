package graphics.quadrangle;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import util.Rectangle;

public abstract class Dynamic extends Vec10quad{
	public static int VBO;
	public static int VAO;
	
	public static final int vboSize = 50;
	
	public Dynamic(Rectangle rect, float[] tint) {
		super(rect, tint);
	}

	@Override
	public void setupVBO() {
		super.setupVBO();
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(getStride() * core.Size.quadVertices * getMax());
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getVBO());
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_DYNAMIC_DRAW);
	}

	@Override
	public int getMax() {
		return vboSize;
	}

	@Override
	public int getNumElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVAO(int VAO) {
		Dynamic.VAO = VAO;
	}

	@Override
	public void setVBO(int VBO) {
		Dynamic.VBO = VBO;
	}

	@Override
	public int getVAO() {
		// TODO Auto-generated method stub
		return Dynamic.VAO;
	}

	@Override
	public int getVBO() {
		// TODO Auto-generated method stub
		return Dynamic.VBO;
	}

	
}
