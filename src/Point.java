
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

	public static Point getMiddlePoint(Point topLeftCorner, Point bottomRightCorner) {
		return new Point((topLeftCorner.x + bottomRightCorner.x)/2, (topLeftCorner.y + bottomRightCorner.y)/2);
	}
}
