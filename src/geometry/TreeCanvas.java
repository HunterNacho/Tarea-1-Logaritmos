package geometry;

import java.awt.Component;
import java.awt.Graphics;

import tree.IRTree;

public class TreeCanvas extends Component {
	
	private static final long serialVersionUID = 5385404418832766968L;
	private IRTree tree;
	
	public TreeCanvas(IRTree tree) {
		this.tree = tree;
	}
	
	@Override
	public void paint(Graphics g) {
		tree.draw(g);
	}
}
