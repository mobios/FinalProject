package graphics;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public abstract class Model {
	protected int vAO;
	protected int vBO;
	
	public void genObj(){
		vAO = GL30.glGenVertexArrays();
		vBO = GL15.glGenBuffers();
	}
	
	public int getVAO(){
		return vAO;
	}
	
	public int getVBO(){
		return vBO;
	}

	public abstract void load(float[] verticies, byte[] indices);
}
