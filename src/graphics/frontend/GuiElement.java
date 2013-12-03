package graphics.frontend;

import graphics.backend.Texture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import util.Rectangle;

public class GuiElement extends BackgroundImage{
	private static List<GuiElement> guiCollection;

	@Override
	public void staticSetup(){
		guiCollection = new ArrayList<GuiElement>();
	}
	
	public GuiElement(){
		
	}
	
	public GuiElement(Rectangle rect, String imagePath) {
		super(rect, imagePath);
		area = rect;
		tint = new float[]{1.f,1.f,1f,1.f};
		
		image = new Texture(imagePath);
		guiCollection.add(this);
	}

	@Override
	public Collection<BackgroundImage> getCollection(){
		return new ArrayList<BackgroundImage>(guiCollection);
	}
	
	public void populate(){
		for(GuiElement bg : guiCollection){
			bg.guiInit();
		}
	}
	
	public void guiInit(){
		buffluc = getLuc();
		reloadVBO();
	}
}
