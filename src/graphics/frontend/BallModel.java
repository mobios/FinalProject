package graphics.frontend;

import coachingTools.Player;
import graphics.backend.Texture;
import util.Point;
import util.Rectangle;

public class BallModel extends PlayerModel {
	public static Texture sprite;
	public static float width = PlayerModel.width/3f;
	public static float height = PlayerModel.height/3f;
	public static float stepVal = 0.04f;
	
	public static final float widthBuf = .04f;
	
	public static Player targetPlayer;
	public static Player withBall;
	
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
	
	public void targetPlayer(Player target){
		targetPlayer = target;
		withBall = null;
	}
	
	public void step(){
		if(targetPlayer == null && withBall != null){
			this.setPosition(withBall.getXRight() + widthBuf, withBall.getDisplay().getRect().getY());
		}
		if(targetPlayer == null)
			return;
		float hypotnuse = (float) Math.sqrt(Math.pow((getRect().getX() - targetPlayer.getDisplay().getRect().getX()),2)+
									Math.pow((getRect().getY() - targetPlayer.getDisplay().getRect().getY()), 2));
		
		double angle = Math.PI + Math.atan((getRect().getY() - targetPlayer.getDisplay().getRect().getY())/(getRect().getX() - targetPlayer.getDisplay().getRect().getX()));
		
		if(hypotnuse < .04f){
			targetPlayer.setBall(this);
			targetPlayer = null;
			return;
		}
		
		this.move((float)(stepVal*Math.cos(angle)), (float)(stepVal*Math.sin(angle)));
	}
}
