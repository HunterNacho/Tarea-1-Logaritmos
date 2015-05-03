package node;

import java.util.ArrayList;

import rnode.RNode;
import tree.IRTree;

public class RootNode extends AbstractNode {

	public RootNode(ArrayList<RNode> elements, int t, IRTree tree) {
		super(elements, t, tree);
	}

	@Override
	protected int getLowerLimit() {
		return 2;
	}

	@Override
	public INode createInstance(ArrayList<RNode> elements) {
		return new RootNode(elements, t, tree);
	}

	public void updateDepth() {
		this.updateDepth(0);
	}

}
