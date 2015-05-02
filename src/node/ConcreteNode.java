package node;

import java.util.ArrayList;

import rnode.RNode;



public class ConcreteNode extends AbstractNode {

	public ConcreteNode(ArrayList<RNode> elements, int t) {
		super(elements, t);
	}

	@Override
	public INode createInstance(ArrayList<RNode> elements) {
		return new ConcreteNode(elements, t);
	}
	
}
