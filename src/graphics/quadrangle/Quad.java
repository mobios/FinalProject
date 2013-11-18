package graphics.quadrangle;

import util.Rectangle;

public abstract class Quad {
	protected Rectangle area;
	protected static int vbo;
	protected static int vao;
	protected static int vibo;
	protected static int indexElementCount;
	
	protected int buffluc;
	
	public static void setup(){
		indexElementCount = Rectangle.elementCount();
	}
	
	public static int getVbo() {
		return vbo;
	}
	
	public static int getVao() {
		return vao;
	}
	
	public static int getVibo() {
		return vibo;
	}
	
	public static void render() {
	}
	
	public abstract void move(float deltx, float delty);
	public abstract void remove(); // DO NOT USE// FORWARD IMPLEMENTATION ONLY
}
