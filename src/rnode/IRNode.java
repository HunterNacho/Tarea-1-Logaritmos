package rnode;

import node.INode;
import geometry.Rectangle;
@Deprecated
public interface IRNode {
	public abstract Rectangle getRectangle();
	public abstract INode getNext();
}
