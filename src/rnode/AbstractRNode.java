package rnode;
import node.INode;
import geometry.Rectangle;

@Deprecated
public abstract class AbstractRNode implements IRNode {
	protected Rectangle rectangle;
	protected INode next;
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	public INode getNext() {
		return next;
	}
}
