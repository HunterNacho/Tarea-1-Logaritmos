package tree;
import java.util.ArrayList;

import rnode.RNode;

import node.ConcreteNode;
import node.INode;
import node.RootNode;
import geometry.Rectangle;

public abstract class AbstractRTree implements IRTree{
	public static final int t = 50;
	public static final double m = 0.4;
	public static final double p = 0.3;
	protected RootNode root;
	
	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		return root.buscar(rectangle);
	}
	
	@Override
	public void insertar(Rectangle rectangle) {
		RNode newNode = root.insertar(rectangle, shouldReinsert());
		if (newNode != null) {
			this.updateRoot(newNode);
		}
	}
	
	@Override
	public void updateRoot(RNode newNode) {
		INode oldRoot = new ConcreteNode(root.getElements(), t);
		RNode aux = new RNode(Rectangle.minimumBoundingRectangle(oldRoot.getRectangles()), oldRoot);
		ArrayList<RNode> newRootElements = new ArrayList<RNode>();
		newRootElements.add(aux);
		newRootElements.add(newNode);
		root = new RootNode(newRootElements, t);
	}
}
