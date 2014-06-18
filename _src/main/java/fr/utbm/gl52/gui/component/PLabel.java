package fr.utbm.gl52.gui.component;

import java.awt.Dimension;

import javax.swing.JLabel;

public class PLabel extends JLabel {

	private static final long serialVersionUID = -607089944110705407L;

	public PLabel(String text){
		super(text);
		setPreferredSize(new Dimension(70, 20));
		setMaximumSize(new Dimension(150, 20));
		setMinimumSize(new Dimension(50, 20));
	}
	
	public PLabel(String text, int minWidth){
		super(text);
		setPreferredSize(new Dimension(70, 20));
		setMaximumSize(new Dimension(150, 20));
		setMinimumSize(new Dimension(minWidth, 20));
	}
	
}
