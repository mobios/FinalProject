package graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture {
	public int textureID = -1;
	public static int textureUnit = GL13.GL_TEXTURE0;
	
	public void generate(){
		textureID = GL11.glGenTextures();
	}
	
	public void generateUniformUnit())
	
	public void load(String path){
		
	}
}
