package geometry;

import java.util.Comparator;

import rnode.RNode;

public class RectangleComparators {
	
	public static final Comparator<RNode> BY_LEFT = new CompareByLeft();
	public static final Comparator<RNode> BY_RIGHT = new CompareByRight();
	public static final Comparator<RNode> BY_TOP = new CompareByTop();
	public static final Comparator<RNode> BY_BOTTOM = new CompareByBottom();
	
	public static class CompareByLeft implements Comparator<RNode> {

		@Override
		public int compare(RNode r1, RNode r2) {
			
			double delta = r1.getRectangle().getLeft() - r2.getRectangle().getLeft();
			if(delta < 0) return -1;
			else if(delta >0) return 1;
			return 0;
		}
		
	}
	
	public static class CompareByRight implements Comparator<RNode> {

		@Override
		public int compare(RNode r1, RNode r2) {
			
			double delta = r1.getRectangle().getRight() - r2.getRectangle().getRight();
			if(delta < 0) return -1;
			else if(delta >0) return 1;
			return 0;
		}
		
	}
	
	public static class CompareByTop implements Comparator<RNode> {

		@Override
		public int compare(RNode r1, RNode r2) {
			
			double delta = r1.getRectangle().getTop() - r2.getRectangle().getTop();
			if(delta < 0) return -1;
			else if(delta >0) return 1;
			return 0;
		}
		
	}
	
	public static class CompareByBottom implements Comparator<RNode> {

		@Override
		public int compare(RNode r1, RNode r2) {
			
			double delta = r1.getRectangle().getBottom() - r2.getRectangle().getBottom();
			if(delta < 0) return -1;
			else if(delta >0) return 1;
			return 0;
		}
		
	}
	
	public static class CompareByDistance implements Comparator <RNode> {
		
		private Rectangle center;
		
		public CompareByDistance(Rectangle center) {
			this.center = center;
		}
		
		@Override
		public int compare(RNode node1, RNode node2) {
			double delta = node2.getRectangle().distanceTo(center) - node1.getRectangle().distanceTo(center);
			if (delta > 0) return 1;
			if (delta < 0) return -1;
			return 0;
		}
		
	}

}
