package geometry;

import java.util.ArrayList;

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
	
	public static Rectangle minimumBoundingRectangle(Rectangle aRectangle, Rectangle anotherRectangle) {
		Point newTopLeft = Point.getTopLeft(aRectangle.topLeftCorner, anotherRectangle.topLeftCorner);
		Point newBottomRight = Point.getBottomRight(aRectangle.bottomRightCorner, anotherRectangle.bottomRightCorner);
		return new Rectangle(newTopLeft, newBottomRight);
	}
	
	public static Rectangle minimumBoundingRectangle(ArrayList<Rectangle> rectangles) {
		assert(rectangles.size() > 0);
		Rectangle result = rectangles.get(0);
		for (int i = 1; i < rectangles.size(); i++) {
			result = minimumBoundingRectangle(result, rectangles.get(i));
		}
		return result;
	}
	
	public double distanceTo(Rectangle other) {
		return this.getMiddlePoint().distanceTo(other.getMiddlePoint());
	}

	public Point getMiddlePoint() {
		return Point.getMiddlePoint(topLeftCorner, bottomRightCorner);
	}
	
	public double getHeight() {
		return Point.getYDistance(topLeftCorner, bottomRightCorner);
	}
	
	public double getWidth() {
		return Point.getXDistance(topLeftCorner, bottomRightCorner);
	}
	
	public double getArea() {
		return getHeight() * getWidth();
	}
	
	
}
