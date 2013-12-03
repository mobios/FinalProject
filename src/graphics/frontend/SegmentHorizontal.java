package graphics.frontend;
import graphics.backend.Texture;
import util.Point;
import util.Rectangle;

public class SegmentHorizontal extends Segment{
	private static final float width = 0.1f;
	private static final float height = 0.01f;
	public static final String spriteURL = "resources/segmentHorizontal.png";
	
	public SegmentHorizontal(Point pt) {
		super(new Rectangle(pt.x, pt.y, width, height), spriteURL);
	}
	
	public SegmentHorizontal() {
		super();
	}
}