package graphics.quadrangle;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import util.Rectangle;

public abstract class Static extends Vec10quad{
	public static int VBO, VAO;
	public static final int vboSize = 50;
	

	public Static(Rectangle rect, float[] tint) {
		super(rect, tint);
	}

	public static void setup() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setupVBO() {
		super.setupVBO();
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(getStride() * core.Size.quadVertices * getMax());
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getVBO());
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_STATIC_DRAW);
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
		Static.VAO = VAO;
	}

	@Override
	public void setVBO(int VBO) {
		Static.VBO = VBO;
	}

	@Override
	public int getVAO() {
		// TODO Auto-generated method stub
		return Static.VAO;
	}

	@Override
	public int getVBO() {
		// TODO Auto-generated method stub
		return Static.VBO;
	}
}
