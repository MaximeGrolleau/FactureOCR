package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.listeners.FileListener;
import fr.utbm.gl52.gui.listeners.ScanListener;

public class DocumentPanel extends JPanel implements FileListener {

	private static final long serialVersionUID = -6019904855963518718L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 320;

	private List<ScanListener> scanListeners = new ArrayList<ScanListener>();
	private File file = null;
	private JPanel imagePane = new JPanel();

	public DocumentPanel(){
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		PButton scanBtn = new PButton("Scan", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireLaunchScan();
			}
		});
		add(scanBtn, BorderLayout.SOUTH);
		add(imagePane, BorderLayout.CENTER);
	}

	public void fileLoaded(File file) {
		this.file = file;
		showFile();
		revalidate();
	}

	private void showFile() {
		System.out
				.println("Nouvelle image chargée : " + file.getAbsolutePath());
		ImageIcon img = new ImageIcon(file.getAbsolutePath());

		JLabel imageLbl = new JLabel(scaleImage(img));
		imagePane.removeAll();
		imagePane.add(imageLbl);
		revalidate();
	}

	private ImageIcon scaleImage(ImageIcon img) {
		int width;
		int height;

		if (img.getIconHeight() < img.getIconWidth()) {
			width = this.getWidth();
			height = this.getWidth() * img.getIconHeight() / img.getIconWidth();
		} else {
			height = this.getHeight();
			width = this.getHeight() * img.getIconWidth() / img.getIconHeight();
		}

		return new ImageIcon(img.getImage().getScaledInstance(width, height,
				Image.SCALE_SMOOTH));
	}

	private void fireLaunchScan() {
		for (ScanListener elt : scanListeners) {
			elt.launchScan();
		}
	}
}
