package util;

public class Rectangle implements Region{
	public float x, y;
	public float width, height;
	private Origin origin;
	
	public float[][] get2dVertices(){
		float[][] ret = new float[4][2];
		if(origin == Origin.CENTER){
			ret[0] = new float[]{x-width/2, y+height/2};
			ret[1] = new float[]{x-width/2, y-height/2};
			ret[3] = new float[]{x+width/2, y-height/2};
			ret[4] = new float[]{x+width/2, y+height/2};
			return ret;
		}
		
		ret[0] = new float[]{x, y};
		ret[1] = new float[]{x, y-height};
		ret[2] = new float[]{x+width, y-height};
		ret[3] = new float[]{x+width, y};
		
		return ret;
	}
	
	public float[] get3dVertices(){
		float[] ret = new float[4*3];
		if(origin == Origin.CENTER){
			ret[0] = x-width/2;
			ret[1] = y+height/2;
			ret[2] = 0;
			
			ret[3] = x-width/2;
			ret[4] = y-height/2;
			ret[5] = 0;
			
			ret[6] = x+width/2;
			ret[7] = y-height/2;
			ret[8] = 0;
			
			ret[9] = x+width/2;
			ret[10] = y+height/2;
			ret[11] = 0;
			return ret;
		}
		
		ret[0] = x;
		ret[1] = y;
		ret[2] = 0;
		
		ret[3] = x;
		ret[4] = y-height;
		ret[5] = 0;
		
		ret[6] = x+width;
		ret[7] = y-height;
		ret[8] = 0;
		
		ret[9] = x+width;
		ret[10] = y;
		ret[11] = 0;
		return ret;
		
	}

	public byte[] getOrder(){
		return new byte[]{0,1,2,2,3,0};
	}
	
	public static int elementCount(){
		return (new Rectangle(0,0,0,0,null)).getOrder().length;
	}
	
	@Override
	public boolean contains(float x, float y) {
		// TODO NEED TO IMPLEMENT CENTER BEHAVIOUR
		if(origin == Origin.TOPLEFT){
			if(x < this.x)
				return false;
			
			if(y < this.y)
				return false;
			
			if(x > this.x - width)
				return false;
			
			if(y > this.y - height)
				return false;
		}
		
		return true;
	}

	public Rectangle(float x, float y, float width, float height, Origin origin) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.origin = origin;
	}

}