package tree;

public class SplitOnlyRTree extends AbstractRTree {
	
	
	public SplitOnlyRTree() {
		super();
	}
	
	public SplitOnlyRTree(int t) {
		super(t);
	}
	
	@Override
	public boolean shouldReinsert() {
		return false;
	}

}
