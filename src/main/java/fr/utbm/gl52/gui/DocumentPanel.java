package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.component.PTextField;
import fr.utbm.gl52.gui.listeners.ScanListener;
import fr.utbm.gl52.model.Model;

public class DocumentPanel extends JPanel {

	private static final long serialVersionUID = -6019904855963518718L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 350;

	private List<ScanListener> scanListeners = new ArrayList<ScanListener>();
	private JPanel imagePane = new JPanel();
	private PTextField filePathFld;
	private PButton scanBtn;
	private PComboBox modelCb;
	
	private File file = null;
	private List<Model> models;
	

	public DocumentPanel(List<Model> models){
		this.models = models;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		imagePane.setBackground(Color.white);
		imagePane.setSize(new Dimension(320, 700));
		imagePane.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel filePropertyPane = new JPanel();
		filePropertyPane.setLayout(new GridBagLayout());
		filePropertyPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(2,2,2,2);
		gc.gridx = 0;
		gc.gridy = 0;	
		
		// filepath
		JLabel filePathLbl = new JLabel("Filepath");
		filePathFld = new PTextField(180);
		filePathFld.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String pathString = filePathFld.getText().trim();
				if(pathString.isEmpty()){
					file = null;
					imagePane.removeAll();
					imagePane.updateUI();
					scanBtn.setEnabled(false);
				} else {
					file = new File(pathString);
					showFile();
				}
			}

		});
		PButton filePathBtn = new PButton("...", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExplorer();
			}
		});
		filePathBtn.setPreferredSize(new Dimension(20, 20));

		// type of doc
		JLabel docTypeLabel = new JLabel("Type of document");
		PComboBox docTypeCb = new PComboBox(new String[] { "Select a type ...",
				"Facture",
				"Ticket de caisse" });
		docTypeCb.setSelectedIndex(1);

		// model
		JLabel modelLabel = new JLabel("Model");
		String[] itemsModelCb = new String[models.size() + 1];
		itemsModelCb[0] = "Select a model ...";
		for(int i = 1; i<models.size()+1; i++){
			itemsModelCb[i] = "Model " + i;
		}
		modelCb = new PComboBox(itemsModelCb);

		JPanel scanPane = new JPanel();
		scanBtn = new PButton("Extract", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = modelCb.getSelectedIndex()-1;
				if(index < 0){
					JOptionPane.showMessageDialog(imagePane, "Please select a model." , "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					fireLaunchScan(index);
				}
			}
		});
		scanBtn.setEnabled(false);
		scanPane.add(scanBtn);
		scanPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		filePropertyPane.add(filePathLbl, gc);
		
		gc.gridx = 1;
		filePropertyPane.add(filePathFld, gc);
		
		gc.gridx = 2;
		filePropertyPane.add(filePathBtn, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		filePropertyPane.add(docTypeLabel, gc);
		
		gc.gridx = 1;
		gc.gridwidth = 2;
		filePropertyPane.add(docTypeCb, gc);
		
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.gridy = 2;
		filePropertyPane.add(modelLabel, gc);
		
		gc.gridx = 1;
		gc.gridwidth = 2;
		filePropertyPane.add(modelCb, gc);
		
		add(filePropertyPane, BorderLayout.NORTH);
		add(imagePane, BorderLayout.CENTER);
		add(scanPane, BorderLayout.SOUTH);
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
		//check existence
		if (file != null) {
			if (file.exists()) {
				//check format
				String format = getFileExtension(file.getName());
				if(isCorrectFormat(format)){
					System.out.println("Nouvelle image charg�e : "
							+ file.getAbsolutePath());
					ImageIcon img = new ImageIcon(file.getAbsolutePath());
					imagePane.removeAll();
					imagePane.add(new JLabel(scaleImage(img)));
					imagePane.updateUI();
					scanBtn.setEnabled(true);
				} else {
					System.out.println("format non support� : "
							+ file.getAbsolutePath());
					file = null;
					JOptionPane.showMessageDialog(this, "Format non support�" , "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				System.out.println("fichier introuvable chemin incorrect : "
						+ file.getAbsolutePath());
				JOptionPane.showMessageDialog(this, "Chemin incorrect" , "Erreur", JOptionPane.WARNING_MESSAGE);
				file = null;
				imagePane.removeAll();
				imagePane.updateUI();
				scanBtn.setEnabled(false);
			}
		}

	}

	private String getFileExtension(String filepath){
		String[] splitpath = filepath.split("\\.");
		return splitpath[1];
	}
	
	private boolean isCorrectFormat(String format){
		switch (format){
		case "png":
			return true;
		case "jpg":
			return true;
		case "pdf":
			return true;
		case "bmp":
			return true;
		case "gif":
			return true;
		case "html":
			return true;
		case "tif":
			return true;
		default :
			return false;
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

	public void addScanListener(ScanListener listener) {
		scanListeners.add(listener);
	}

	public void removeScanListener(ScanListener listener) {
		scanListeners.remove(listener);
	}
	
	private void fireLaunchScan(int indexModel) {
		for (ScanListener elt : scanListeners) {
			System.out.println("envoie de demande de scan");
			if(file != null){
				elt.launchScan(file, models.get(indexModel));
			}
		}
	}
}
