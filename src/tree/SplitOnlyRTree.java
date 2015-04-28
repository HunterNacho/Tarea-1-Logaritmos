package tree;
import geometry.Rectangle;


public class SplitOnlyRTree extends AbstractRTree {

	@Override
	public void insertar(Rectangle rectangle) {
		root.insertar(rectangle, false);
		
	}

}
