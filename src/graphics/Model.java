package graphics;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public abstract class Model {
	protected int vAO;
	protected int vBO;
	private boolean closed = false;
	
	public Model(){
		genObj();
	}
	
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
	
	public void finalize(){
		if(!closed)
			close();
		
		try {
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public abstract void render();
	public abstract void close();
	public abstract void load(float[] verticies, byte[] indices);
}
