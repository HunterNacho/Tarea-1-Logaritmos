package geometry;

public class Point{
	
	private double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(Point other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	public static Point getTopLeft(Point point1, Point point2) {
		return new Point(Math.min(point1.x, point2.x), Math.max(point1.y, point2.y));
	}
	
	public static Point getBottomRight(Point point1, Point point2) {
		return new Point(Math.max(point1.x, point2.x), Math.min(point1.y, point2.y));
	}

	public static Point getMiddlePoint(Point point1, Point point2) {
		return new Point((point1.x + point2.x)/2, (point1.y + point2.y)/2);
	}
	
	public static double getXDistance(Point point1, Point point2) {
		return Math.abs(point1.x - point2.x);		
	}
	
	public static double getYDistance(Point point1, Point point2) {
		return Math.abs(point1.y - point2.y);
	}
	
	@Deprecated
	public boolean isInside(Rectangle rectangle) {
		return x > rectangle.getTopLeftCorner().x && x < rectangle.getBottomRightCorner().x
				&& y > rectangle.getBottomRightCorner().y && y < rectangle.getTopLeftCorner().y;
	}
}
