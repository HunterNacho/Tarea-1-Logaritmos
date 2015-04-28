package node;

import geometry.Rectangle;

import java.util.ArrayList;

public interface INode {
	public abstract boolean underflow();
	public abstract boolean overflow();
	public abstract ArrayList<Rectangle> buscar(Rectangle rectangle);
	public abstract void insertar(Rectangle rectangle, boolean shouldReinsert);
}
