package fr.utbm.gl52.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utbm.gl52.gui.component.ArticleTableModel;
import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.component.PTextField;
import fr.utbm.gl52.gui.listeners.DocumentListener;
import fr.utbm.gl52.gui.listeners.FileListener;

public class ExtractedDataPanel extends JPanel{

	private static final long serialVersionUID = -9133411730442747891L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 380;
	
	private List<FileListener> fileListeners = new ArrayList<FileListener>();
	private List<DocumentListener> documentListeners = new ArrayList<DocumentListener>();

	private PTextField filePathFld;
	private PTextField clientFld;
	private PTextField storeFld;
	private PTextField dateFld;
	private PTextField companyFld;

	public ExtractedDataPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//filepath
		JLabel filePathLbl = new JLabel("Filepath");
		filePathFld = new PTextField();
		PButton filePathBtn = new PButton("...", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExplorer();
			}
		});
		filePathBtn.setPreferredSize(new Dimension(20, 20));
		JPanel filePathPane = new JPanel();
		filePathPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		filePathPane.add(filePathLbl);
		filePathPane.add(filePathFld);
		filePathPane.add(filePathBtn);
		add(filePathPane);
		
		//type of doc
		JLabel docTypeLabel = new JLabel("Type of document");
		PComboBox docTypeCb = new PComboBox(new String[] { "Facture",
				"Ticket de caisse" });
		JPanel docTypePane = new JPanel();
		docTypePane.setLayout(new FlowLayout(FlowLayout.LEFT));
		docTypePane.add(docTypeLabel);
		docTypePane.add(docTypeCb);
		add(docTypePane);
		
		//model
		JLabel modelLabel = new JLabel("Model");
		PComboBox modelCb = new PComboBox(new String[] { "select a model ..." });
		JPanel modelPane = new JPanel();
		modelPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		modelPane.add(modelLabel);
		modelPane.add(modelCb);
		add(modelPane);
		
		// client
		JLabel clientLbl = new JLabel("Client");
		clientFld = new PTextField();
		JPanel clientPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		clientPane.add(clientLbl);
		clientPane.add(clientFld);
		add(clientPane);

		//company
		JLabel companyLbl = new JLabel("Company");
		companyFld = new PTextField();
		JPanel companyPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		companyPane.add(companyLbl);
		companyPane.add(companyFld);
		add(companyPane);
		
		// lieu de vente
		JLabel storeLbl = new JLabel("Store");
		storeFld = new PTextField();
		JPanel storePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		storePane.add(storeLbl);
		storePane.add(storeFld);
		add(storePane);

		//date
		JLabel dateLbl = new JLabel("Date");
		dateFld = new PTextField();
		JPanel datePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		datePane.add(dateLbl);
		datePane.add(dateFld);
		add(datePane);		
		
		//articles table
		ArticleTableModel articleTable = new ArticleTableModel();
		add(articleTable);
		
		//total
		JLabel totalLbl = new JLabel("Total");
		PTextField totalFld = new PTextField();
		totalFld.setEditable(false);
		PComboBox totalCb = new PComboBox(new String[] { "EUR", "USD", "GBP" });
		JPanel totalPane = new JPanel();
		totalPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		totalPane.add(totalLbl);
		totalPane.add(totalFld);
		totalPane.add(totalCb);
		add(totalPane);
		
		//buttons
		PButton saveBtn = new PButton("Save", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireSaveDoc();
			}
		});
		PButton delBtn = new PButton("Delete", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireDeleteDoc();
			}
		});
		PButton cancelBtn = new PButton("Cancel all modifications",
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fireCancelModifsDoc();
					}
				});
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPane.add(saveBtn);
		btnPane.add(delBtn);
		btnPane.add(cancelBtn);
		add(btnPane);		
	}

	private void openExplorer() {
		JFileChooser fileChooser = new JFileChooser();
		int ok = fileChooser.showOpenDialog(this);
		if (ok == JFileChooser.APPROVE_OPTION) {
			filePathFld
					.setText(fileChooser.getSelectedFile().getAbsolutePath());
			fireNewFileLoaded(fileChooser.getSelectedFile());
		}
	}

	public void addFileListener(FileListener listener) {
		fileListeners.add(listener);
	}

	public void removeFileListener(FileListener listener) {
		fileListeners.remove(listener);
	}

	private void fireNewFileLoaded(File file) {
		for (FileListener elt : fileListeners) {
			elt.fileLoaded(file);
		}
	}

	public void addDocumentListener(DocumentListener listener) {
		documentListeners.add(listener);
	}

	public void removeDocumentListener(DocumentListener listener) {
		documentListeners.remove(listener);
	}

	private void fireSaveDoc() {
		for (DocumentListener elt : documentListeners) {
			elt.saveDocument();
		}
	}

	private void fireDeleteDoc() {
		for (DocumentListener elt : documentListeners) {
			elt.deleteDocument();
		}
	}

	private void fireCancelModifsDoc() {
		for (DocumentListener elt : documentListeners) {
			elt.cancelModifsDocument();
		}
	}
}
