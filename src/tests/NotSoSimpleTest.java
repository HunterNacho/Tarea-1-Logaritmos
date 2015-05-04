package tests;

import geometry.Rectangle;
import geometry.TreeCanvas;

import javax.swing.JFrame;

import tree.IRTree;
import tree.SplitOnlyRTree;
import tree.SplitReinsertRTree;

public class NotSoSimpleTest {
	public static void main(String[] args) {
		int t = 5;
		final int TICKS = 10000;
		IRTree splitTree = new SplitOnlyRTree(t);
		IRTree reinsertTree = new SplitReinsertRTree(t);
		double  x1, y1, x2, y2;
		for (int i = 1; i <= 500000; i++) {
			x1 = Math.random()*550 + 10;
			y1 = Math.random()*550 + 10;
			x2 = x1 + 5;
			y2 = y1 + 5;
			splitTree.insertar(new Rectangle(x1, y1, x2, y2));
			reinsertTree.insertar(new Rectangle(x1, y1, x2, y2));
			if ((i % TICKS) == 0) {
				System.out.println("Insertados " + i + " rectángulos.");
			}
		}
		JFrame window1 = new JFrame();
		window1.setTitle("Split Only.");
	    window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window1.setBounds(0, 0, 600, 600);
	    window1.getContentPane().add(new TreeCanvas(splitTree));
	    window1.setVisible(true);
	    JFrame window2 = new JFrame();
		window2.setTitle("Split and Reinsert.");
	    window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window2.setBounds(0, 0, 600, 600);
	    window2.getContentPane().add(new TreeCanvas(reinsertTree));
	    window2.setVisible(true);
	}
}
