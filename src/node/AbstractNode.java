package node;
import geometry.Rectangle;
import geometry.RectangleComparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rnode.RNode;
import tree.AbstractRTree;

 
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
		return getRectangles(elements);
	}
	
	public static ArrayList<Rectangle> getRectangles(List<RNode> elements) {
		ArrayList<Rectangle> rectChildren = new ArrayList<Rectangle>();
		for(RNode r : elements)
			rectChildren.add(r.getRectangle());
		return rectChildren;
	}
	
	public RNode split() {
		
		int extreme = (int)Math.floor(AbstractRTree.m*elements.size());
		ArrayList<Double> left = new ArrayList<Double>();
		ArrayList<Double> right = new ArrayList<Double>();
		ArrayList<Double> top = new ArrayList<Double>();
		ArrayList<Double> bottom = new ArrayList<Double>();
		
		ArrayList<RNode> leftSorted, rightSorted, topSorted, bottomSorted;
		leftSorted = new ArrayList<RNode>(elements);
		rightSorted = new ArrayList<RNode>(elements);
		topSorted = new ArrayList<RNode>(elements);
		bottomSorted = new ArrayList<RNode>(elements);
		
		Collections.sort(leftSorted, RectangleComparators.BY_LEFT);
		Collections.sort(topSorted, RectangleComparators.BY_TOP);
		Collections.sort(bottomSorted, RectangleComparators.BY_BOTTOM);
		Collections.sort(rightSorted, RectangleComparators.BY_RIGHT);
		
		
		for(int j=extreme; j<=(elements.size()-extreme); j++) {
			double mbrIzqLeft = Rectangle.minimumBoundingRectangle(getRectangles(leftSorted.subList(0, j+1))).getPerimeter();
			double mbrDerLeft = Rectangle.minimumBoundingRectangle(getRectangles(leftSorted.subList(j+1, elements.size()))).getPerimeter();
			
			double mbrIzqRight = Rectangle.minimumBoundingRectangle(getRectangles(rightSorted.subList(0, j+1))).getPerimeter();
			double mbrDerRight = Rectangle.minimumBoundingRectangle(getRectangles(rightSorted.subList(j+1, elements.size()))).getPerimeter();
			
			double mbrIzqTop = Rectangle.minimumBoundingRectangle(getRectangles(topSorted.subList(0, j+1))).getPerimeter();
			double mbrDerTop = Rectangle.minimumBoundingRectangle(getRectangles(topSorted.subList(j+1, elements.size()))).getPerimeter();
			
			double mbrIzqBottom = Rectangle.minimumBoundingRectangle(getRectangles(bottomSorted.subList(0, j+1))).getPerimeter();
			double mbrDerBottom = Rectangle.minimumBoundingRectangle(getRectangles(bottomSorted.subList(j+1, elements.size()))).getPerimeter();
			
			left.add(mbrIzqLeft + mbrDerLeft);
			right.add(mbrIzqRight + mbrDerRight);
			top.add(mbrIzqTop + mbrDerTop);
			bottom.add(mbrIzqBottom + mbrDerBottom);			
			
		}
		
		double minLeft = Collections.min(left);
		double minRight = Collections.min(right);
		double minTop = Collections.min(top);
		double minBottom = Collections.min(bottom);
		
		double minX = (minLeft <= minRight)? minLeft : minRight;
		double minY = (minTop <= minBottom)? minTop : minBottom;
		
		ArrayList<RNode> toSplit = (minX <= minY)? leftSorted : bottomSorted;
		
		double minInter = Double.MAX_VALUE;
		double minSumArea = Double.MAX_VALUE;
		int index = extreme;
		
		for(int j=extreme; j<=(toSplit.size()-extreme); j++) {
			
			Rectangle mbrIzq = Rectangle.minimumBoundingRectangle(getRectangles(toSplit.subList(0, j+1)));
			Rectangle mbrDer = Rectangle.minimumBoundingRectangle(getRectangles(toSplit.subList(j+1, toSplit.size())));
			
			double intersection = Rectangle.commonArea(mbrIzq, mbrDer);
			
			
			if(intersection == minInter && (mbrIzq.getArea()+mbrDer.getArea() < minSumArea) 
					|| intersection < minInter) {
					minSumArea = mbrIzq.getArea()+mbrDer.getArea();
					minInter = intersection;
					index = j;
			}			
		}
		return null;
	}
}
