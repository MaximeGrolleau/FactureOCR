package fr.utbm.gl52.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utbm.gl52.gui.component.ArticleTableModel;
import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.component.PTextField;
import fr.utbm.gl52.gui.listeners.DocumentListener;
import fr.utbm.gl52.gui.listeners.ScanListener;

public class ExtractedDataPanel extends JPanel implements ScanListener {

	private static final long serialVersionUID = -9133411730442747891L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 320;
	
	private List<DocumentListener> documentListeners = new ArrayList<DocumentListener>();

	private boolean isFileLoaded = false;
	private PTextField clientFld;
	private PTextField storeFld;
	private PTextField dateFld;
	private PTextField companyFld;
	private PButton saveBtn;
	private PButton delBtn;
	private PButton cancelBtn;

	public ExtractedDataPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// client
		JLabel clientLbl = new JLabel("Client");
		clientFld = new PTextField();
		JPanel clientPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		clientPane.add(clientLbl);
		clientPane.add(clientFld);
		add(clientPane);

		//company
		JLabel companyLbl = new JLabel("Company");
		companyFld = new PTextField();
		JPanel companyPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		companyPane.add(companyLbl);
		companyPane.add(companyFld);
		add(companyPane);
		
		// lieu de vente
		JLabel storeLbl = new JLabel("Store");
		storeFld = new PTextField();
		JPanel storePane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		storePane.add(storeLbl);
		storePane.add(storeFld);
		add(storePane);

		//date
		JLabel dateLbl = new JLabel("Date");
		dateFld = new PTextField();
		JPanel datePane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		datePane.add(dateLbl);
		datePane.add(dateFld);
		add(datePane);		
		
		//articles table
		ArticleTableModel articleTable = new ArticleTableModel();
		add(articleTable);
		
		// total HT
		JLabel totalLbl = new JLabel("Total HT: ");
		PTextField totalFld = new PTextField();
		totalFld.setEditable(false);
		PComboBox totalCb = new PComboBox(new String[] { "EUR", "USD", "GBP" });
		totalCb.setPreferredSize(new Dimension(60, 20));
		JPanel totalPane = new JPanel();
		totalPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		totalPane.add(totalLbl);
		totalPane.add(totalFld);
		totalPane.add(totalCb);
		add(totalPane);
		
		// total TTC
		JLabel totalTTCLbl = new JLabel("Total TTC: ");
		PTextField totalTTCFld = new PTextField();
		totalTTCFld.setEditable(false);
		PComboBox totalTTCCb = new PComboBox(
				new String[] { "EUR", "USD", "GBP" });
		totalTTCCb.setPreferredSize(new Dimension(60, 20));
		JPanel totalTTCPane = new JPanel();
		totalTTCPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		totalTTCPane.add(totalTTCLbl);
		totalTTCPane.add(totalTTCFld);
		totalTTCPane.add(totalTTCCb);
		add(totalTTCPane);

		//buttons
		saveBtn = new PButton("Save", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireSaveDoc();
			}
		});
		delBtn = new PButton("Delete", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireDeleteDoc();
			}
		});
		cancelBtn = new PButton("Cancel all modifications",
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fireCancelModifsDoc();
					}
				});
		activateButtons(false, false, false);

		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPane.add(saveBtn);
		btnPane.add(delBtn);
		btnPane.add(cancelBtn);
		add(btnPane);
		this.setEnabled(isFileLoaded);
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

	public void launchScan() {
		isFileLoaded = true;
		this.setEnabled(isFileLoaded);
	}

	private void activateButtons(boolean save, boolean del, boolean cancel) {
		this.saveBtn.setEnabled(save);
		this.delBtn.setEnabled(del);
		this.cancelBtn.setEnabled(cancel);
	}
}
