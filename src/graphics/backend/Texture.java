package graphics.backend;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture {
	public int textureID = -1;
	public static int textureUnit = GL13.GL_TEXTURE0;
	public static final int channels = 4;
	
	public void generate(){
		textureID = GL11.glGenTextures();
	}
		
	public Texture(String path){
		if(TextureManager.isLoaded(path)){
			textureID = TextureManager.getIndex(path);
			return;
		}
		
		TextureManager.load(path, load(path));
	}
	
	private int load(String path){
		generate();
		ByteBuffer textureBuffer = null;
		int imageWidth =0;
		int imageHeight =0;
		
		try{
			InputStream texIn = new FileInputStream(path);
			PNGDecoder decoder = new PNGDecoder(texIn);
			imageWidth = decoder.getWidth();
			imageHeight = decoder.getHeight();
			
			textureBuffer = ByteBuffer.allocateDirect(channels * imageWidth * imageHeight);
			decoder.decode(textureBuffer, imageWidth*channels, Format.RGBA);
			textureBuffer.flip();
			texIn.close();
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(3);
		}
		
		GL13.glActiveTexture(textureUnit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, imageWidth, imageHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureBuffer);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		return textureID;
	}
}
