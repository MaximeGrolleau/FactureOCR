package fr.utbm.gl52.gui.component;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class PComboBox extends JComboBox<Object> {

	private static final long serialVersionUID = -7564387139367739977L;

	public PComboBox(Object[] elements) {
		super();
		for (Object elt : elements) {
			addItem(elt);
		}
		setPreferredSize(new Dimension(150, 20));
	}
}
