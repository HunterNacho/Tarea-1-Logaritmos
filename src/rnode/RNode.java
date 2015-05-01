package rnode;

import geometry.Rectangle;
import node.INode;

public class RNode {
	protected Rectangle rectangle;
	protected INode next;
	
	public RNode(Rectangle rectangle, INode next) {
		this.rectangle = rectangle;
		this.next = next;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public INode getNext() {
		return next;
	}
}
