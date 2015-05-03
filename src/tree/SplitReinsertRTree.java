package tree;

public class SplitReinsertRTree extends AbstractRTree {
	
	public SplitReinsertRTree() {
		super();
	}
	
	public SplitReinsertRTree(int t) {
		super(t);
	}
	
	@Override
	public boolean shouldReinsert() {
		return true;
	}
	
}
