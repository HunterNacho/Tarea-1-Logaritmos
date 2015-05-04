package tree;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import rnode.RNode;

public interface IRTree {

	public ArrayList<Rectangle> buscar(Rectangle rectangle);
	public void insertar(Rectangle rectangle);
	public void updateRoot(RNode newNode);
	public boolean shouldReinsert();
	public void reinsert(ArrayList<RNode> elements, int insertionDepth);
	public boolean getOverflow(int depth);
	public void draw(Graphics g);
	public Color getColor(int depth);
}
