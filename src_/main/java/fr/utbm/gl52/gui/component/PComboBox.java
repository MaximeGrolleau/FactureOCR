package fr.utbm.gl52.gui.component;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class PComboBox extends JComboBox<String> {

	private static final long serialVersionUID = -7564387139367739977L;

	public PComboBox(String[] elements) {
		super();
		for (String elt : elements) {
			addItem(elt);
		}
		setPreferredSize(new Dimension(150, 20));
	}
}
