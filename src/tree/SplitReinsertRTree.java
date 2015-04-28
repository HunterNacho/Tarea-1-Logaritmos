package tree;
import geometry.Rectangle;


public class SplitReinsertRTree extends AbstractRTree {

	@Override
	public void insertar(Rectangle rectangle) {
		root.insertar(rectangle, true);		
	}

}
