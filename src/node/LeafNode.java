package node;

import geometry.Rectangle;

import java.util.ArrayList;

import rnode.RNode;
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
	
	
}
