package tree;
import java.util.ArrayList;

import node.INode;
import geometry.Rectangle;

public abstract class AbstractRTree implements IRTree{
	protected final int t = 50;
	protected final double m = 0.4;
	protected final double p = 0.3;
	protected INode root;
	
	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		return root.buscar(rectangle);
	}
	
}
