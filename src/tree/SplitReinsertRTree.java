package tree;
import geometry.Rectangle;


public class SplitReinsertRTree extends AbstractRTree {

	@Override
	public boolean shouldReinsert() {
		return true;
	}

}
