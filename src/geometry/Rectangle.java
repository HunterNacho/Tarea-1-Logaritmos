package geometry;

import java.util.ArrayList;

public class Rectangle {
	
	private double left, top, right, bottom;
	
	public Rectangle(double x1, double y1, double x2, double y2) {
		left = Math.min(x1, x2);
		top = Math.max(y1, y2);
		right = Math.max(x1, x2);
		bottom = Math.min(y1, y2);
	}
	
	public static Rectangle minimumBoundingRectangle(Rectangle aRectangle, Rectangle anotherRectangle) {
		double newTop = Math.max(aRectangle.top, anotherRectangle.top);
		double newBottom = Math.min(aRectangle.bottom, anotherRectangle.bottom);
		double newLeft = Math.min(aRectangle.left, anotherRectangle.left);
		double newRight = Math.max(aRectangle.right, anotherRectangle.right);
		return new Rectangle(newLeft, newRight, newBottom, newTop);
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
		return new Point((left + right)/2, (top + bottom)/2);
	}
	
	public double getHeight() {
		return top - bottom;
	}
	
	public double getWidth() {
		return right - left;
	}
	
	public double getArea() {
		return getHeight() * getWidth();
	}
	
	public Point getTopLeftCorner() {
		return new Point(left, top);
	}
	
	public Point getBottomRightCorner() {
		return new Point(right, bottom);
	}
	
	public Point getTopRightCorner() {
		return new Point(right, top);
	}
	
	public Point getBottomLeftCorner() {
		return new Point(left, bottom);
	}
	
	@Deprecated
	public boolean isContainedBy(Rectangle other) {
		return getTopLeftCorner().isInside(other) ||
				getTopRightCorner().isInside(other) ||
				getBottomLeftCorner().isInside(other) ||
				getBottomRightCorner().isInside(other);
	}
	
	public boolean intersects(Rectangle other){
		return !((right <= other.left) ||
				(left >= other.right) ||
				(bottom >= other.top) ||
				(top <= other.bottom));
	}
	

}
