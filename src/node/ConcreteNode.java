package node;

import java.awt.Color;
import java.util.ArrayList;

import rnode.RNode;
import tree.IRTree;



public class ConcreteNode extends AbstractNode {

	public ConcreteNode(ArrayList<RNode> elements, int t, IRTree tree) {
		super(elements, t, tree);
	}

	@Override
	public INode createInstance(ArrayList<RNode> elements) {
		return new ConcreteNode(elements, t, tree);
	}

	@Override
	public Color drawColor() {
		return Color.YELLOW;
	}
	
}
