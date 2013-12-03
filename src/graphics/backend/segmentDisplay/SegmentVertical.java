package graphics.backend.segmentDisplay;
import util.Point;
import util.Rectangle;
import graphics.backend.Texture;

public class SegmentVertical extends Segment{
	private static final float width = SCALE/10f;
	private static final float height = SCALE;
	public static final String spriteURL = "resources/segmentVertical.png";
	
	public SegmentVertical(Point pt) {
		super(new Rectangle(pt.x, pt.y, width, height), spriteURL);
	}
	
	public SegmentVertical() {
		super();
	}
}