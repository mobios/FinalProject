package graphics.frontend;

import graphics.Texture;
import graphics.quadrangle.Static;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import util.Rectangle;

public class BackgroundImage extends Static{
	private Texture image;
	private static int nextluc = 0;
	
	private static List<BackgroundImage> allBackground;
	
	public BackgroundImage(Rectangle rect, String imagePath){
		this(rect, (float[])null);
		image = new Texture(imagePath);
		allBackground.add(this);
	}
	
	private BackgroundImage(Rectangle rect, float[] tint) {
		super(rect, tint);
		// TODO Auto-generated constructor stub
	}

	public BackgroundImage(){
		this(null,(float[])null);
	}
	
	@Override
	public void staticSetup() {
		super.setupVAO();
		allBackground = new ArrayList<BackgroundImage>();
	}

	@Override
	public void bindTexture() {
		GL13.glActiveTexture(Texture.textureUnit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, image.textureID);
	}

	@Override
	public int getLuc() {
		int templuc = nextluc;
		nextluc++;
		return templuc;
	}
	
	@Override
	public int getNumObjects(){
		return 3;
	}
	
	public void blit(){
		bindTexture();
		GL11.glDrawElements(GL11.GL_TRIANGLES, indexElementCount, GL11.GL_UNSIGNED_BYTE, indexElementCount*buffluc);
	}
	
	@Override
	public void render(){
		setupBatch();
		for(BackgroundImage bg : allBackground){
			bg.blit();
		}
	}
}
