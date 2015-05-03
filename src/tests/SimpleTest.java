package tests;

import javax.swing.JFrame;

import geometry.Rectangle;
import geometry.TreeCanvas;
import tree.*;

public class SimpleTest {
	public static void main(String[] args) {
		IRTree tree = new SplitOnlyRTree(3);
//		for (int i = 1; i <= 12; i++) {
//			tree.insertar(new Rectangle(i*20, i*20, i*20 + 15, i*20 + 15));
//		}
//		
//		for(int i = 24; i>12; i--) {
//			tree.insertar(new Rectangle(i*20, i*20, i*20 + 15, i*20 + 15));
//		}
		
		for (int i = 1; i <= 24; i++) {
			tree.insertar(new Rectangle(i*20, i*20, i*20 + 15, i*20 + 15));
		}
		JFrame window = new JFrame();
		window.setTitle("Split Only,secuencial");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(0, 0, 600, 600);
	    window.getContentPane().add(new TreeCanvas(tree));
	    window.setVisible(true);
		System.out.println("Done");
	}
}
