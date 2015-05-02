package node;

import java.util.ArrayList;

import rnode.RNode;

public class RootNode extends AbstractNode {

	public RootNode(ArrayList<RNode> elements, int t) {
		super(elements, t);
	}

	@Override
	protected int getLowerLimit() {
		return 2;
	}

	@Override
	public INode createInstance(ArrayList<RNode> elements) {
		return new RootNode(elements, t);
	}

}
