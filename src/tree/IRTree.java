package tree;
import geometry.Rectangle;

import java.awt.Graphics;
import java.util.ArrayList;

import rnode.RNode;

public interface IRTree {

	public ArrayList<Rectangle> buscar(Rectangle rectangle);
	public void insertar(Rectangle rectangle);
	public void updateRoot(RNode newNode);
	public boolean shouldReinsert();
	public void updateDepth();
	public void reinsert(ArrayList<RNode> elements);
	public boolean getOverflow(int depth);
	public void draw(Graphics g);
}
