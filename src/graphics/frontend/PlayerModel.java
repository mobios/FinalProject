package graphics.frontend;

import util.Rectangle;
import graphics.Image;
import graphics.quadrangle.dynamicQuad;

public class PlayerModel extends dynamicQuad{
	public static Image sprite;
	public static String spriteURL;
	
	public PlayerModel(Rectangle rect, float[] tint) {
		super(rect, tint);
		
	}

	@Override
	public void staticSetup(){
		super.staticSetup();
		
	}
}
