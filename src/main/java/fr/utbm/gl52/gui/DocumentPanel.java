package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.component.PTextField;
import fr.utbm.gl52.gui.listeners.ScanListener;

public class DocumentPanel extends JPanel {

	private static final long serialVersionUID = -6019904855963518718L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 350;

	private List<ScanListener> scanListeners = new ArrayList<ScanListener>();
	private File file = null;
	private JPanel imagePane = new JPanel();
	private PTextField filePathFld;
	private PButton scanBtn;

	public DocumentPanel(){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		imagePane.setBackground(Color.GRAY);
		imagePane.setSize(new Dimension(320, 700));
		imagePane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		JPanel filePropertyPane = new JPanel();
		filePropertyPane.setLayout(new BoxLayout(filePropertyPane,
				BoxLayout.Y_AXIS));

		// filepath
		JLabel filePathLbl = new JLabel("Filepath");
		filePathFld = new PTextField();
		filePathFld.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				file = new File(filePathFld.getText());
				showFile();
			}

		});
		PButton filePathBtn = new PButton("...", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExplorer();
			}
		});
		filePathBtn.setPreferredSize(new Dimension(20, 20));
		JPanel filePathPane = new JPanel();
		filePathPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		filePathPane.add(filePathLbl);
		filePathPane.add(filePathFld);
		filePathPane.add(filePathBtn);
		filePropertyPane.add(filePathPane);

		// type of doc
		JLabel docTypeLabel = new JLabel("Type of document");
		PComboBox docTypeCb = new PComboBox(new String[] { "Select a type ...",
				"Facture",
				"Ticket de caisse" });
		JPanel docTypePane = new JPanel();
		docTypePane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		docTypePane.add(docTypeLabel);
		docTypePane.add(docTypeCb);
		filePropertyPane.add(docTypePane);

		// model
		JLabel modelLabel = new JLabel("Model");
		PComboBox modelCb = new PComboBox(new String[] { "Select a model ..." });
		JPanel modelPane = new JPanel();
		modelPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		modelPane.add(modelLabel);
		modelPane.add(modelCb);
		filePropertyPane.add(modelPane);


		scanBtn = new PButton("Scan", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireLaunchScan();
			}
		});
		scanBtn.setEnabled(false);

		add(filePropertyPane, BorderLayout.NORTH);
		add(imagePane, BorderLayout.CENTER);
		add(scanBtn, BorderLayout.SOUTH);
	}

	private void openExplorer() {
		JFileChooser fileChooser = new JFileChooser();
		int ok = fileChooser.showOpenDialog(this);
		if (ok == JFileChooser.APPROVE_OPTION) {
			filePathFld
					.setText(fileChooser.getSelectedFile().getAbsolutePath());
			this.file = fileChooser.getSelectedFile();
			showFile();
		}
	}

	private void showFile() {
		if (file != null) {
			if (file.exists()) {
				System.out.println("Nouvelle image chargée : "
						+ file.getAbsolutePath());
				ImageIcon img = new ImageIcon(file.getAbsolutePath());

				imagePane.removeAll();
				imagePane.add(new JLabel(scaleImage(img)));
				imagePane.updateUI();
				scanBtn.setEnabled(true);
			} else {
				System.out.println("fichier introuvable chemin incorrect");
				file = null;
				imagePane.removeAll();
				imagePane.updateUI();
				scanBtn.setEnabled(false);
			}
		}
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
