package fr.utbm.gl52.gui.component;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PButton extends JButton {

	private static final long serialVersionUID = -3950289604036489966L;

	public PButton(String text, ActionListener listener) {
		super(text);
		addActionListener(listener);
	}

}
