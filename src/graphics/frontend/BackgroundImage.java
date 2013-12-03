package graphics.frontend;

import graphics.backend.Texture;
import graphics.quadrangle.Static;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import util.Rectangle;

public class BackgroundImage extends Static{
	protected Texture image;
	private static int nextluc = 0;
	
	protected static List<BackgroundImage> allBackground;
	
	public BackgroundImage(Rectangle rect, String imagePath){
		this(rect, (float[])null);
		image = new Texture(imagePath);
		allBackground.add(this);
	}
	
	private BackgroundImage(Rectangle rect, float[] tint) {
		super(rect, tint);
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
		int templuc = BackgroundImage.nextluc;
		BackgroundImage.nextluc++;
		return templuc;
	}
	
	@Override
	public int getNumObjects(){
		return ((BackgroundImage.nextluc == 0) ? getMax() : BackgroundImage.nextluc);
	}
	
	public void blit(){
		bindTexture();
		GL11.glDrawElements(GL11.GL_TRIANGLES, indexElementCount, GL11.GL_UNSIGNED_BYTE, indexElementCount*buffluc);
	}
	
	@Override
	public void render(){
		setupBatch();
		for(BackgroundImage bg : getCollection()){
			bg.blit();
		}
	}
	
	public Collection<BackgroundImage> getCollection(){
		return allBackground;
	}
	
	public Texture getTexture(){
		return image;
	}
	
	protected void setTexture(Texture texture){
		image = texture;
	}
}
