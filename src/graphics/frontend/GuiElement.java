package graphics.frontend;

import graphics.backend.Texture;

import java.util.ArrayList;
import java.util.List;

import util.Rectangle;

public abstract class GuiElement extends BackgroundImage{
	private static List<GuiElement> guiCollection;

	@Override
	public void staticSetup(){
		guiCollection = new ArrayList<GuiElement>();
	}
	
	public GuiElement(Rectangle rect, String imagePath) {
		area = rect;
		tint = new float[]{1.f,1.f,1f,1.f};
		buffluc = getLuc();
		
		image = new Texture(imagePath);
		guiCollection.add(this);
	}

}
