package fr.utbm.gl52.gui.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class PTextField extends JTextField{

	private static final long serialVersionUID = -7117579735239354539L;

	public PTextField() {
		super();
		setPreferredSize(new Dimension(150, 20));
		setMinimumSize(new Dimension(90, 20));
		setForeground(Color.black);
	}
	
	public PTextField(int width) {
		super();
		setPreferredSize(new Dimension(width, 20));
		setForeground(Color.black);
	}
}
