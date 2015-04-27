package node;
import geometry.Rectangle;

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
	
	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for (IRNode element : elements) 
			if (element.getRectangle().intersects(rectangle)) 
				result.addAll(element.getNext().buscar(rectangle));
		return result;
	}
	
}
