package graphics.frontend;

import graphics.backend.Texture;
import graphics.quadrangle.Dynamic;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import util.Rectangle;

public class PlayerModel extends Dynamic{
	public static Texture sprite;
	public static String spriteURL = "resources/player.png";
	private static int nextluc = 0;
	
	public PlayerModel(Rectangle rect, float[] tint) {
		super(rect, tint);
	}

	public PlayerModel(){
		this(null, null);
	}
	
	@Override
	public void bindTexture() {
		GL13.glActiveTexture(Texture.textureUnit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, sprite.textureID);
	}

	@Override
	public void staticSetup() {
		super.setupVAO();
		sprite = new Texture(spriteURL);
	}

	@Override
	public int getLuc() {
		int templuc = PlayerModel.nextluc;
		PlayerModel.nextluc++;
		return templuc;
	}
	
	@Override
	public int getNumObjects(){
		return ((PlayerModel.nextluc == 0) ? getMax() : PlayerModel.nextluc);
	}
}
