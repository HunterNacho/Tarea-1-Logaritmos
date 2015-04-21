
public class Rectangle {
	
	private Point topLeftCorner, bottomRightCorner;
	
	public Rectangle(Point topLeftCorner, Point bottomRightCorner) {
		this.topLeftCorner = topLeftCorner;
		this.bottomRightCorner = bottomRightCorner;
	}
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		topLeftCorner = new Point(Math.min(x1, x2), Math.max(y1, y2));
		bottomRightCorner = new Point(Math.max(x1, x2), Math.min(y1, y2));
	}
	
	public Rectangle minimumBoundingRectangle(Rectangle anotherRectangle) {
		Point newTopLeft = Point.getTopLeft(topLeftCorner, anotherRectangle.topLeftCorner);
		Point newBottomRight = Point.getBottomRight(bottomRightCorner, anotherRectangle.bottomRightCorner);
		return new Rectangle(newTopLeft, newBottomRight);
	}
}
