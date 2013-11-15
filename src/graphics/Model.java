package graphics;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public abstract class Model {
	private boolean closed = false;

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
	public abstract void load();
}
