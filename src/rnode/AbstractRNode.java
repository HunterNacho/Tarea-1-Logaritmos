package rnode;
import geometry.Rectangle;
import tree.IRTree;


public abstract class AbstractRNode implements IRNode {
	protected Rectangle rectangle;
	protected IRTree next;
}
