package node;

import geometry.Rectangle;
import geometry.RectangleComparators;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import rnode.RNode;
import tree.AbstractRTree;
import tree.IRTree;

public class RootNode extends AbstractNode {

	public RootNode(ArrayList<RNode> elements, int t, IRTree tree) {
		super(elements, t, tree);
		this.depth = 0;
	}

	@Override
	protected int getLowerLimit() {
		return 2;
	}

	@Override
	public INode createInstance(ArrayList<RNode> elements) {
		if (isLeaf())
			return new LeafNode(elements, t, tree);
		else
			return new ConcreteNode(elements, t, tree);
	}
	
	@Override
	public RNode insertar(Rectangle rectangle, boolean shouldReinsert) {
		if (isLeaf()) {
			elements.add(new RNode(rectangle, null));
			if (overflow()){
				if (shouldReinsert && !tree.getOverflow(getDepth())) {
					Collections.sort(elements, new RectangleComparators.CompareByDistance(Rectangle.minimumBoundingRectangle(getRectangles())));
					int extreme = (int) Math.floor(AbstractRTree.p*elements.size());
					ArrayList<RNode> toReinsert = new ArrayList<RNode>(elements.subList(0, extreme + 1));
					elements = new ArrayList<RNode>(elements.subList(extreme + 1, elements.size()));
					tree.reinsert(toReinsert, getDepth());
					return null;
				}
				else
					return this.split();
			}
			return null;
		}
		else
			return super.insertar(rectangle, shouldReinsert);
	}
	
	@Override
	public boolean isLeaf() {
		return elements.isEmpty() || elements.get(0).getNext() == null;
	}	
	
	@Override
	public int getDepth() {
		if (isLeaf()) return 0;
		return super.getDepth();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		Rectangle.minimumBoundingRectangle(getRectangles()).draw(g);
		super.draw(g);
	}
	
}
