package fr.utbm.gl52.gui;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utbm.gl52.document.DocumentType;
import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.component.PTextField;
import fr.utbm.gl52.gui.listeners.ScanListener;
import fr.utbm.gl52.model.ImageArea;
import fr.utbm.gl52.model.Model;
import fr.utbm.gl52.model.Tag;

public class DocumentPanel extends JPanel {

	private static final long serialVersionUID = -6019904855963518718L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 350;

	private List<ScanListener> scanListeners = new ArrayList<ScanListener>();
	private JLayeredPane imagePane = new JLayeredPane();
	private PTextField filePathFld;
	private PButton scanBtn;
	private PComboBox modelCb;
	private DocumentType typeOfDoc = DocumentType.DFLT;
	private int imgOriginWidth, imgOriginHeight, imgScaledWidth, imgScaledHeight;
	private File file = null;
	private List<Model> modelsBill = new ArrayList<Model>();
	private List<Model> modelsReceipt = new ArrayList<Model>();
	

	public DocumentPanel(final List<Model> models){
		for(Model model : models){
			if(model.getType() == DocumentType.BILL){
				this.modelsBill.add(model);			
			} else {
				modelsReceipt.add(model);
			}
		}
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		imagePane.setBackground(Color.white);
		imagePane.setPreferredSize(new Dimension(320, 700));
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
		final PComboBox docTypeCb = new PComboBox(DocumentType.getList());
		docTypeCb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				typeOfDoc = DocumentType.getDocumentType((String) docTypeCb.getSelectedItem());
				modelCb.removeAllItems();
				if(typeOfDoc == DocumentType.BILL){
					for(int i = 0; i<modelsBill.size(); i++){
						modelCb.addItem(modelsBill.get(i).getName());
					}
				} else {
					for(int i = 0; i<modelsReceipt.size(); i++){
						modelCb.addItem(modelsReceipt.get(i).getName());
					}
				}
			}
		});
		//docTypeCb.setSelectedIndex(1);

		// model
		JLabel modelLabel = new JLabel("Model");
		modelCb = new PComboBox();
		modelCb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setModelFilterPanel();
			}
			
		});

		JPanel scanPane = new JPanel();
		scanBtn = new PButton("Extract", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = modelCb.getSelectedIndex();
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
					JLabel imgLbl = new JLabel(scaleImage(img));
					JPanel imgPane = new JPanel();
					imgPane.setLayout(new BorderLayout());
					imgPane.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
					imgPane.setOpaque(false);
					imgPane.setBounds(0,0,imagePane.getWidth(), imagePane.getHeight());
					imgPane.add(imgLbl, BorderLayout.CENTER);
					imagePane.add(imgPane, -10);
					setModelFilterPanel();
					imagePane.updateUI();
					scanBtn.setEnabled(true);
				} else {
					System.out.println("format non support� : "
							+ file.getAbsolutePath());
					file = null;
					JOptionPane.showMessageDialog(this, "Unsupported Format" , "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				System.out.println("fichier introuvable chemin incorrect : "
						+ file.getAbsolutePath());
				JOptionPane.showMessageDialog(this, "Filepath leads nowhere" , "Erreur", JOptionPane.WARNING_MESSAGE);
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

		imgOriginWidth = img.getIconWidth();
		imgOriginHeight = img.getIconHeight();
		
		if ((double) (imagePane.getWidth()-2)/(double) (imagePane.getHeight()-2) < (double) img.getIconWidth()/ (double)img.getIconHeight()) {
			imgScaledWidth = imagePane.getWidth();
			imgScaledHeight = (imagePane.getWidth()) * img.getIconHeight() / img.getIconWidth();
		} else {
			imgScaledHeight = imagePane.getHeight();
			imgScaledWidth = (imagePane.getHeight()) * img.getIconWidth() / img.getIconHeight();
		}

		return new ImageIcon(img.getImage().getScaledInstance(imgScaledWidth, imgScaledHeight,
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
			if(file != null){
				if(typeOfDoc == DocumentType.BILL){
					elt.launchScan(file, modelsBill.get(indexModel));
				} else {
					elt.launchScan(file, modelsReceipt.get(indexModel));
				}
			}
		}
	}
	
	public void setModelFilterPanel(){
		if(modelCb.getSelectedIndex() > -1){
			if(imagePane.getComponentCount() > 1){
				imagePane.remove(0);
			}
			if(typeOfDoc == DocumentType.BILL){
				imagePane.add(new PRectanglePane((float) 0.2, modelsBill.get(modelCb.getSelectedIndex())), 0);
			} else {

				imagePane.add(new PRectanglePane((float) 0.2, modelsReceipt.get(modelCb.getSelectedIndex())), 0);
			}
		}
	}
	
	class PRectanglePane extends JPanel{
		private static final long serialVersionUID = -8544206245654358747L;

		private float transparency;

        private Model model;
        
        public PRectanglePane(float transparency, Model model){
            this.transparency = transparency;
            this.model = model;
			this.transparency = transparency;
			setBounds(0,0,imagePane.getWidth()-2,imagePane.getHeight()-2);
			setOpaque(false);
		}
		@Override
	    public void paintComponent(Graphics g) {
		    Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g);
            g2.setColor(Color.ORANGE);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
            for(Tag tag : model.getTags()){
                ImageArea zone = tag.getLocation().getArea();
                int xup, yup, xlow, ylow;
               	xup = (int) (zone.getFromX()*imgScaledWidth) / imgOriginWidth;
                yup = (int) (zone.getFromY()*imgScaledHeight) / imgOriginHeight;
                xlow = (int) (zone.getToX()*imgScaledWidth) / imgOriginWidth;;
                ylow = (int) (zone.getToY()*imgScaledHeight) / imgOriginHeight;
                g2.fillRect(xup, yup, xlow, ylow);                
            }
            for(Tag tag : model.getProductTags()){
            	g2.setColor(Color.GREEN);
                ImageArea zone = tag.getLocation().getArea();
                int xup, yup, xlow, ylow;
               	xup = (int) (zone.getFromX()*imgScaledWidth) / imgOriginWidth;
                yup = (int) (zone.getFromY()*imgScaledHeight) / imgOriginHeight;
                xlow = (int) (zone.getToX()*imgScaledWidth) / imgOriginWidth;;
                ylow = (int) (zone.getToY()*imgScaledHeight) / imgOriginHeight;
                g2.fillRect(xup, yup, xlow, ylow);        
            }
		}
	}
}
