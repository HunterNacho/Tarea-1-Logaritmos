import java.util.ArrayList;


public abstract class AbstractNode implements INode {
	
	protected int t;
	protected ArrayList<Rectangle> rectangles;
	
	protected abstract int getLowerLimit();
	
	@Override
	public boolean underflow() {
		return rectangles.size() < this.getLowerLimit();
	}

	@Override
	public boolean overflow() {
		return rectangles.size() > 2*t;
	}
}
