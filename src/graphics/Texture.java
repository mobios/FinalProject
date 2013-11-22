package graphics;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture {
	public int textureID = -1;
	public static int textureUnit = GL13.GL_TEXTURE0;
	public static final int channels = 4;
	
	public void generate(){
		textureID = GL11.glGenTextures();
	}
		
	public void load(String path){
		generate();
		ByteBuffer textureBuffer = BufferUtils.createByteBuffer(0);
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
		GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, imageWidth, imageHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureBuffer);
	}
}
