package util;

public class Rectangle implements Region{
	float x, y;
	public float width, height;
	private Origin origin;
	
	public float[] get2dVertices(){
		@SuppressWarnings("unchecked")
		float[] ret = new float[4*2];
		if(origin == Origin.CENTER){
			ret[0] = x-width/2;
			ret[1] = y+height/2;
			ret[2] = x-width/2;
			ret[3] = y-height/2;
			ret[4] = x+width/2;
			ret[5] = y-height/2;
			ret[6] = x+width/2;
			ret[7] = y+height/2;
			return ret;
		}
		
		ret[0] = x;
		ret[1] = y;
		
		ret[2] = x;
		ret[3] = y-height;
		
		ret[4] = x+width;
		ret[5] = y-height;
		
		ret[6] = x+width;
		ret[7] = y;
		return ret;
	}

	@Override
	public boolean contains(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

}