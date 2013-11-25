package graphics.quadrangle;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import util.Rectangle;

public abstract class Dynamic extends Vec10quad{
	public Dynamic(Rectangle rect, float[] tint) {
		super(rect, tint);
	}

	@Override
	public void setupVBO() {
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(getStride() * core.Size.quadVertices * getMax());
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getVBO());
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexBuffer, GL15.GL_DYNAMIC_DRAW);
	}

	
}
