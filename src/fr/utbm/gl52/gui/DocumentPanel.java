package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utbm.gl52.gui.listeners.FileListener;

public class DocumentPanel extends JPanel implements FileListener {

	private static final long serialVersionUID = -6019904855963518718L;

	private File file = null;

	public DocumentPanel(){
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(390,650));
	}

	public void fileLoaded(File file) {
		this.file = file;
		showFile();
		revalidate();
	}

	private void showFile() {
		System.out.println("image chargée : " + file.getAbsolutePath());
		ImageIcon img = new ImageIcon(file.getAbsolutePath());
		JLabel lblImg = new JLabel(img);
		add(lblImg, BorderLayout.CENTER);
	}
}
