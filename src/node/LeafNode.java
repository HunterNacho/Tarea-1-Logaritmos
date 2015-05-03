package node;

import geometry.Rectangle;
import geometry.RectangleComparators;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import rnode.RNode;
import tree.AbstractRTree;
import tree.IRTree;



public class LeafNode extends AbstractNode {
	
	public LeafNode(ArrayList<RNode> elements, int t, IRTree tree) {
		super(elements, t, tree);
	}

	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for (RNode element : elements) 
			if (element.getRectangle().intersects(rectangle)) 
				result.add(element.getRectangle());
		return result;
	}

	@Override
	public RNode insertar(Rectangle rectangle, boolean shouldReinsert) {
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
	
	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public INode createInstance(ArrayList<RNode> elements) {
		return new LeafNode(elements, t, tree);
	}
	
	@Override
	public int getDepth() {
		return 0;
	}
	
	@Override
	public Color drawColor() {
		return Color.GREEN;
	}
}
