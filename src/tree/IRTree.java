package tree;
import geometry.Rectangle;

import java.util.ArrayList;

public interface IRTree {

	public abstract ArrayList<Rectangle> buscar(Rectangle rectangle);
	public abstract void insertar(Rectangle rectangle);
}
