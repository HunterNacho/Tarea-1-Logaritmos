package tree;
import rnode.RNode;
import geometry.Rectangle;


public class SplitOnlyRTree extends AbstractRTree {

	@Override
	public boolean shouldReinsert() {
		return false;
	}

}
