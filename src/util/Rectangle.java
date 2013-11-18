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
	
	public float[][] get3dVertices(){
		float[][] ret = new float[core.Size.quadVertices][core.Size.pvs];
		if(origin == Origin.CENTER){
			ret[0][0] = x-width/2;
			ret[0][1] = y+height/2;
			ret[0][2] = 0;
			
			ret[1][0] = x-width/2;
			ret[1][1] = y-height/2;
			ret[1][2] = 0;
			
			ret[2][0] = x+width/2;
			ret[2][1] = y-height/2;
			ret[2][2] = 0;
			
			ret[3][0] = x+width/2;
			ret[3][1] = y+height/2;
			ret[3][2] = 0;
			return ret;
		}
		
		ret[0][0] = x;
		ret[0][1] = y;
		ret[0][2] = 0;
		
		ret[1][0] = x;
		ret[1][1] = y-height;
		ret[1][2] = 0;
		
		ret[2][0] = x+width;
		ret[2][1] = y-height;
		ret[2][2] = 0;
		
		ret[3][0] = x+width;
		ret[3][1] = y;
		ret[3][2] = 0;
		return ret;
		
	}

	public float[][] get3dWithUV(){
		float[][] ret = new float[elementCount()][core.Size.mvs+core.Size.pvs];
		ret = this.get3dVertices();
		
		ret[0][3] = 0;
		ret[0][4] = 0;

		ret[1][3] = 0;
		ret[1][4] = 1;

		ret[2][3] = 1;
		ret[2][4] = 1;

		ret[3][3] = 1;
		ret[3][4] = 0;
	
		
		return ret;
	}
	
	public static byte[] getOrder(){
		return new byte[]{0,1,2,2,3,0};
	}
	
	public static int elementCount(){
		return getOrder().length;
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

	public Rectangle(float x, float y, float width, float height, Origin origin){
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.origin = (origin == null ? Origin.CENTER : origin);
	}

}