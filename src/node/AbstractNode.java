package node;
import geometry.Rectangle;

import java.util.ArrayList;

import rnode.RNode;


public abstract class AbstractNode implements INode {
	
	protected int t;
	protected ArrayList<RNode> elements;
	
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
		for (RNode element : elements) 
			if (element.getRectangle().intersects(rectangle)) 
				result.addAll(element.getNext().buscar(rectangle));
		return result;
	}
	
	@Override
	public void insertar(Rectangle rectangle, boolean shouldReinsert) {
		if(elements.get(0).getNext().isLeaf()) {
			ArrayList<Double> minOverlaps = new ArrayList<Double>();
			double minOverlap = Double.MAX_VALUE;
			for(RNode n : elements) {
//				if(rectangle.overlap())
				
			}
			
		}
		else {
			
		}
	}
	
	@Override
	public boolean isLeaf(){
		return false;
	}
	
}
