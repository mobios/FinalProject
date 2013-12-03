package graphics.frontend;

import graphics.backend.Texture;
import util.Point;
import util.Rectangle;

public class BallModel extends PlayerModel {
	public static Texture sprite;
	public static float width = PlayerModel.width/3f;
	public static float height = PlayerModel.height/3f;
	
	public BallModel(Point pt, float[] tint){
		super(new Rectangle(pt.x, pt.y, width, height), tint);
	}
	
	@Override
	public void staticSetup() {
		BallModel.sprite = new Texture(getURL());
	}
	
	@Override
	public int getTextureID(){
		return BallModel.sprite.textureID;
	}
	
}
