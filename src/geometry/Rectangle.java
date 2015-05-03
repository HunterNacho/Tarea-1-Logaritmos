package geometry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Rectangle {
	
	private double left, top, right, bottom;
	
	public Rectangle(double x1, double y1, double x2, double y2) {
		left = Math.min(x1, x2);
		top = Math.max(y1, y2);
		right = Math.max(x1, x2);
		bottom = Math.min(y1, y2);
	}
	
	public Rectangle extendWith(Rectangle rectangle) {
		return minimumBoundingRectangle(this, rectangle);
	}
	
	public static Rectangle minimumBoundingRectangle(Rectangle aRectangle, Rectangle anotherRectangle) {
		double newTop = Math.max(aRectangle.top, anotherRectangle.top);
		double newBottom = Math.min(aRectangle.bottom, anotherRectangle.bottom);
		double newLeft = Math.min(aRectangle.left, anotherRectangle.left);
		double newRight = Math.max(aRectangle.right, anotherRectangle.right);
		return new Rectangle(newLeft, newTop, newRight, newBottom);
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
	public double getPerimeter() {
		return 2*(getHeight() + getWidth());
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
	
	public double getLeft() {
		return left;
	}

	public double getTop() {
		return top;
	}

	public double getRight() {
		return right;
	}

	public double getBottom() {
		return bottom;
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
	
	public static double commonArea(Rectangle rectangle1, Rectangle rectangle2) {
		if(!rectangle1.intersects(rectangle2))
			return 0;
		double xOverlap = Math.min(rectangle1.right, rectangle2.right) - Math.max(rectangle1.left, rectangle2.left);
		double yOverlap = Math.min(rectangle1.top, rectangle2.top) - Math.max(rectangle1.bottom, rectangle2.bottom);
		return yOverlap * xOverlap;
	}
	
	public double overlap(ArrayList<Rectangle> siblings) {
		double sum = 0;
		for (Rectangle rectangle : siblings) 
			sum += commonArea(this, rectangle);
		sum -= this.getArea();
		return sum;
	}

	public void draw(Graphics g) {
		((Graphics2D) g).draw(new Rectangle2D.Double(left , bottom, getWidth(), getHeight()));
	}
	
}
