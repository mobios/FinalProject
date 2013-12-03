package graphics.frontend;

import graphics.backend.segmentDisplay.SevenSegmentDisplay;
import util.Point;

public class ScoreDisplay {
	private SevenSegmentDisplay team1Display, team2Display;
	private float spacing;
	
	public ScoreDisplay(Point center) {
		spacing = 0.13f;
		team1Display = new SevenSegmentDisplay(new Point(center.x - spacing/2f, center.y));
		team2Display = new SevenSegmentDisplay(new Point(center.x + spacing/2f, center.y));
	}

}
