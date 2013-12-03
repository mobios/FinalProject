package graphics.backend.segmentDisplay;
import graphics.backend.Texture;
import util.Point;
import util.Rectangle;

public class SegmentHorizontal extends Segment{
	private static final float width = SCALE;
	private static final float height = SCALE/10f;
	public static final String spriteURL = "resources/segmentHorizontal.png";
	
	public SegmentHorizontal(Point pt) {
		super(new Rectangle(pt.x, pt.y, width, height), spriteURL);
	}
	
	public SegmentHorizontal() {
		super();
	}
}