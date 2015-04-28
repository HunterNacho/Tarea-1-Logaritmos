package node;

import geometry.Rectangle;

import java.util.ArrayList;

import rnode.RNode;



public class LeafNode extends AbstractNode {
	
	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for (RNode element : elements) 
			if (element.getRectangle().intersects(rectangle)) 
				result.add(element.getRectangle());
		return result;
	}

	@Override
	public void insertar(Rectangle rectangle, boolean shouldReinsert) {
		elements.add(new RNode(rectangle, null));
		if (overflow()){
//			onOverflow();
		}
		
	}
	
	@Override
	public boolean isLeaf() {
		return true;
	}
}
