package tree;
import node.INode;


public abstract class AbstractRTree implements IRTree{
	protected final int t = 50;
	protected final double m = 0.4;
	protected final double p = 0.3;
	protected INode root;

}
