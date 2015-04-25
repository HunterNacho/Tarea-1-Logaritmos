package node;
import java.util.ArrayList;

import rnode.IRNode;


public abstract class AbstractNode implements INode {
	
	protected int t;
	protected ArrayList<IRNode> elements;
	
	protected int getLowerLimit() {
		return t;
	}
	
	@Override
	public boolean underflow() {
		return elements.size() < this.getLowerLimit();
	}

	@Override
	public boolean overflow() {
		return elements.size() > 2*t;
	}
}
