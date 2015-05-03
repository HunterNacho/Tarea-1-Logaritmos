package node;

import geometry.Rectangle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import rnode.RNode;

public interface INode {
	public boolean underflow();
	public boolean overflow();
	public ArrayList<Rectangle> buscar(Rectangle rectangle);
	public RNode insertar(Rectangle rectangle, boolean shouldReinsert);
	public RNode insertar(RNode node, int insertionDepth);
	public boolean isLeaf();
	public ArrayList<Rectangle> getRectangles();
	public INode createInstance(ArrayList<RNode> elements);
	public ArrayList<RNode> getElements();
	public int getDepth();
	public void updateDepth(int i);
	public void draw(Graphics g);
	public Color drawColor();
}
