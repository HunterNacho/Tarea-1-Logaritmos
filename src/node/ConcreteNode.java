package node;

import java.util.List;

import rnode.RNode;



public class ConcreteNode extends AbstractNode {

	public ConcreteNode(List<RNode> elements, int t) {
		super(elements, t);
	}

	@Override
	public INode createInstance(List<RNode> elements) {
		return new ConcreteNode(elements, t);
	}
	
}
