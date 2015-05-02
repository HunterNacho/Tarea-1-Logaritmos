package node;

import java.util.List;

import rnode.RNode;

public class RootNode extends AbstractNode {

	protected RootNode(List<RNode> elements, int t) {
		super(elements, t);
	}

	@Override
	protected int getLowerLimit() {
		return 2;
	}

	@Override
	public INode createInstance(List<RNode> elements) {
		return new RootNode(elements, t);
	}

}
