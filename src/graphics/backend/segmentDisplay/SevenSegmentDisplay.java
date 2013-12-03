package graphics.backend.segmentDisplay;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import graphics.backend.Texture;
import graphics.quadrangle.Dynamic;
import util.Point;
import util.Rectangle;

public class SevenSegmentDisplay{
	private boolean[] seg;
	private ArrayList<Segment> segments;
	private Point center;
	private float[] tintOn, tintOff;
	private static final float X_OFFSET = Segment.SCALE;
	private static final float Y_OFFSET = Segment.SCALE*1.5f;
	
	public SevenSegmentDisplay(Point center){
		segments = new ArrayList<Segment>();
		seg = new boolean[7];
		Arrays.fill(seg, true);
		this.center = center;
		
		segments.add(0, new SegmentHorizontal(new Point(center.x, center.y + Y_OFFSET)));
		segments.add(1, new SegmentVertical(new Point(center.x + X_OFFSET/2f, center.y + Y_OFFSET/2f)));
		segments.add(2, new SegmentVertical(new Point(center.x + X_OFFSET/2f, center.y - Y_OFFSET/2f)));
		segments.add(3, new SegmentHorizontal(new Point(center.x, center.y - Y_OFFSET)));
		segments.add(4, new SegmentVertical(new Point(center.x - X_OFFSET/2f, center.y - Y_OFFSET/2f)));
		segments.add(5, new SegmentVertical(new Point(center.x - X_OFFSET/2f, center.y + Y_OFFSET/2f)));
		segments.add(6, new SegmentHorizontal(new Point(center.x, center.y)));
	}
	
	public void writeValue(int n){
        switch (n)
        {
            case 0:
            	Arrays.fill(seg, true);
        		seg[6] = false;
                break;
            case 1:
            	Arrays.fill(seg, true);
            	seg[0] = false;
        		seg[3] = false;
        		seg[4] = false;
        		seg[5] = false;
        		seg[6] = false;
                break;
            case 2:
            	Arrays.fill(seg, true);
        		seg[2] = false;
        		seg[5] = false;
                break;
            case 3:
            	Arrays.fill(seg, true);
        		seg[4] = false;
        		seg[5] = false;
                break;
            case 4:
            	Arrays.fill(seg, true);
            	seg[0] = false;
        		seg[3] = false;
        		seg[4] = false;
                break;
            case 5:
            	Arrays.fill(seg, true);
        		seg[1] = false;
        		seg[4] = false;
                break;
            case 6:
            	Arrays.fill(seg, true);
        		seg[1] = false;
                break;
            case 7:
            	Arrays.fill(seg, true);
        		seg[3] = false;
        		seg[4] = false;
        		seg[5] = false;
        		seg[6] = false;
                break;
            case 8:
            	Arrays.fill(seg, true);
                break;
            case 9:
            	Arrays.fill(seg, true);
        		seg[4] = false;
                break;
            default:
            	Arrays.fill(seg, true);
                break;
	        }
        
        updateDisplay();
	  }
	
	public void updateDisplay(){
		for (int i = 0; i < seg.length; i++){
			if(seg[i])
				segments.get(i).turnOn();
			else
				segments.get(i).turnOff();
		}
	}
}
