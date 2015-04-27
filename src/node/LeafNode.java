package node;

import geometry.Rectangle;

import java.util.ArrayList;

import rnode.IRNode;



public class LeafNode extends AbstractNode {
	
	@Override
	public ArrayList<Rectangle> buscar(Rectangle rectangle) {
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		for (IRNode element : elements) 
			if (element.getRectangle().intersects(rectangle)) 
				result.add(element.getRectangle());
		return result;
	}
}
