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
		RNode next;
		if(elements.get(0).getNext().isLeaf()) {
			ArrayList<RNode> minDeltaRNodes = new ArrayList<RNode>();
			double minDelta = Double.MAX_VALUE;
			for(RNode n : elements) {
				double overlapIni = n.getRectangle().overlap(this.getRectangles());
				double overlapFin = n.getRectangle().extendWith(rectangle).overlap(this.getRectangles());
				double delta = overlapFin - overlapIni; 
				if(delta <= minDelta) {
					if(delta == minDelta) { 
						minDeltaRNodes.add(n);
					}
					else {
						minDeltaRNodes.clear();
						minDeltaRNodes.add(n);
						minDelta = delta;
					}					
				}							
			}
			
			if(minDeltaRNodes.size() > 1) 
				next = getMinDeltaArea(minDeltaRNodes, rectangle);	
			else
				next = minDeltaRNodes.get(0);	
			
		}
		else {
			next = getMinDeltaArea(elements, rectangle);
		}
		next.getNext().insertar(rectangle, shouldReinsert);
		next.updateMbr();
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}
	
	public static RNode getMinDeltaArea(ArrayList<RNode> rNodes, Rectangle toInsert) {
		ArrayList<RNode> minDeltaRNodes = new ArrayList<RNode>();
		double minDeltaArea = Double.MAX_VALUE;
		for(RNode n : rNodes) {
			double areaIni = n.getRectangle().getArea();
			double areaFin = n.getRectangle().extendWith(toInsert).getArea();
			double delta = areaFin - areaIni; 
			if(delta <= minDeltaArea) {
				if(delta == minDeltaArea) { 
					minDeltaRNodes.add(n);
				}
				else {
					minDeltaRNodes.clear();
					minDeltaRNodes.add(n);
					minDeltaArea = delta;
				}					
			}		
		}
		
		RNode minAreaRNode = null;
		double minArea = Double.MAX_VALUE;
		for(RNode r : minDeltaRNodes) {
			if(r.getRectangle().getArea() < minArea) {
				minAreaRNode = r;
				minArea = r.getRectangle().getArea();
			}
		}
		
		return minAreaRNode;		
	}
	
	public ArrayList<Rectangle> getRectangles() {
		ArrayList<Rectangle> rectChildren = new ArrayList<Rectangle>();
		for(RNode r : elements)
			rectChildren.add(r.getRectangle());
		return rectChildren;
	}
}
