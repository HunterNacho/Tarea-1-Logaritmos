package tree;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import rnode.RNode;

import node.INode;
import node.RootNode;
import geometry.Rectangle;

public abstract class AbstractRTree implements IRTree{
	public int t;
	public static final double m = 0.4;
	public static final double p = 0.3;
	protected RootNode root;
	public ArrayList<Boolean> overflow = new ArrayList<Boolean>();
	
	protected AbstractRTree(int t) {
		this.t = t;
		overflow.add(false);
		root = new RootNode(new ArrayList<RNode>(), t, this);
	}
	
	protected AbstractRTree() {
		this(50);
	}
	
	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		return root.buscar(rectangle);
	}
	
	@Override
	public void insertar(Rectangle rectangle) {
		RNode newNode = root.insertar(rectangle, shouldReinsert());
		if (newNode != null)
			this.updateRoot(newNode);
		for (int i = 0; i < overflow.size(); i++)
			overflow.set(i, false);
	}
	
	@Override
	public void updateRoot(RNode newNode) {
		INode oldRoot = root.createInstance(root.getElements());
		RNode aux = new RNode(Rectangle.minimumBoundingRectangle(oldRoot.getRectangles()), oldRoot);
		ArrayList<RNode> newRootElements = new ArrayList<RNode>();
		newRootElements.add(aux);
		newRootElements.add(newNode);
		root = new RootNode(newRootElements, t, this);
		updateDepth();
	}
	
	@Override
	public void updateDepth() {
		root.updateDepth();
	}
	
	@Override
	public void reinsert(ArrayList<RNode> elements) {
		int insertionDepth = elements.get(0).getNext().getDepth() - 1;
		overflow.set(insertionDepth, true);
		Collections.reverse(elements);
		for (RNode node : elements) {
			RNode newNode = root.insertar(node, insertionDepth);
			if (newNode != null)
				updateRoot(newNode);
				
		}
	}
	
	@Override
	public boolean getOverflow(int depth) {
		return overflow.size() > depth && overflow.get(depth);
	}
	
	@Override
	public String toString() {
		return "[ " + root.toString() + "]";
	}
	
	@Override
	public void draw(Graphics g) {
		root.draw(g);
	}
	
}
