package util;


public class Rectangle implements Region{
	private float x, y;
	private float width, height;
	
	public float[][] get2dVertices(){
		float[][] ret = new float[4][2];
		ret[0] = new float[]{x-width/2, y+height/2};
		ret[1] = new float[]{x-width/2, y-height/2};
		ret[3] = new float[]{x+width/2, y-height/2};
		ret[4] = new float[]{x+width/2, y+height/2};
		return ret;
	}
	
	public float[][] get3dVertices(){
		float[][] ret = new float[core.Size.quadVertices][core.Size.pvs];
		
		ret[0][0] = x-width/2;
		ret[0][1] = y+height/2;
		ret[0][2] = 0;
		ret[0][3] = 1;
		
		ret[1][0] = x-width/2;
		ret[1][1] = y-height/2;
		ret[1][2] = 0;
		ret[1][3] = 1;
		
		ret[2][0] = x+width/2;
		ret[2][1] = y-height/2;
		ret[2][2] = 0;
		ret[2][3] = 1;
		
		ret[3][0] = x+width/2;
		ret[3][1] = y+height/2;
		ret[3][2] = 0;
		ret[3][3] = 1;
		return ret;
	}

	public float[][] get3dWithUV(){
		float[][] ret = new float[core.Size.quadVertices][core.Size.mvs+core.Size.pvs];
		float[][] oldvert = this.get3dVertices();
		
		for(int i =0; i < oldvert.length; i++){
			for(int ii =0; ii < oldvert[i].length; ii++)
				ret[i][ii] = oldvert[i][ii];
		}
		
		ret[0][4] = 0;
		ret[0][5] = 0;

		ret[1][4] = 0;
		ret[1][5] = 1;

		ret[2][4] = 1;
		ret[2][5] = 1;

		ret[3][4] = 1;
		ret[3][5] = 0;
	
		
		return ret;
	}
	
	public static byte[] getOrder(){
		return getOrder(0);
	}
	
	public static byte[] getOrder(int offset){
		byte [] ret = new byte[]{0,1,2,2,3,0};
		for(int i = 0; i < ret.length; i++)
			ret[i] += offset;
		return ret;
	}
	
	public static int elementCount(){
		return getOrder().length;
	}
	
	public void move(float deltx, float delty){
		x += deltx;
		y += delty;
	}


	@Override
	public boolean contains(float x, float y) {
			if(this.x-width/2 > x || this.x+width/2 < x)
				return false;

			if(this.y-height/2 > y || this.y+height/2 < y)
				return false;

		return true;
	}

	public Rectangle(float x, float y, float width, float height){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height*4f/3f;
	}
	

	public float getX() {
		return x;
	}
	

	public float getY() {
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}

	public float getWidth() {
		return width;
	}
	

	public float getHeight() {
		return height;
	}
}