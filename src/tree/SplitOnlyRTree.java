package tree;

public class SplitOnlyRTree extends AbstractRTree {

	@Override
	public boolean shouldReinsert() {
		return false;
	}

}
