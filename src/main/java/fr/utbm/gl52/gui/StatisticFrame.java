package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import fr.utbm.gl52.gui.component.PLabel;

public class StatisticFrame extends JFrame {

	private static final long serialVersionUID = 6185042608913818440L;

	public StatisticFrame() {
		setTitle("Statistics");
		setSize(new Dimension(100,50));
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.setPreferredSize(new Dimension(100,50));
		content.add(new PLabel("Coming soon ..."), BorderLayout.CENTER);
	}

}
