package tree;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
	private ArrayList<Color> colors;
	private int colorIndex;
	
	protected AbstractRTree(int t) {
		this.t = t;
		overflow.add(false);
		root = new RootNode(new ArrayList<RNode>(), t, this);
		colors = new ArrayList<Color>();
		colors.add(Color.RED);
		colors.add(Color.PINK);
		colors.add(Color.ORANGE);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
		colors.add(Color.MAGENTA);
		colors.add(Color.CYAN);
		colors.add(Color.BLUE);
//		colors.add(Color.BLACK);
//		colors.add(Color.DARK_GRAY);
//		colors.add(Color.GRAY);
//		colors.add(Color.LIGHT_GRAY);
		resetColor();
	}
	

	protected AbstractRTree() {
		this(50);
	}
	
	public void resetColor() {
		colorIndex = 0;
	}
	
	public Color nextColor() {
		Color color = colors.get(colorIndex);
		colorIndex = (colorIndex + 1) % colors.size();
		return color;
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
		overflow.add(false);
	}
	
	@Override
	public void reinsert(ArrayList<RNode> elements, int insertionDepth) {
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
	public void draw(Graphics g) {
		root.draw(g);
	}
	
}
