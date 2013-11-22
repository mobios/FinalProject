package graphics.backend;

import org.lwjgl.opengl.GL30;

public class GLState {
	public static int boundVAO;
	
	public static void bindVAO(int VAO){
		if(boundVAO == VAO)
			return;
		GL30.glBindVertexArray(VAO);
		boundVAO = VAO;
	}
}
