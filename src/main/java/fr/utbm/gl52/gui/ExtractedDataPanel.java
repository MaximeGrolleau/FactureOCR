package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import fr.utbm.gl52.document.Currency;
import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.document.DocumentInfo;
import fr.utbm.gl52.document.Price;
import fr.utbm.gl52.gui.component.ArticleTableModel;
import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.component.PLabel;
import fr.utbm.gl52.gui.component.PPane;
import fr.utbm.gl52.gui.component.PTextField;
import fr.utbm.gl52.gui.listeners.DocumentListener;
import fr.utbm.gl52.gui.listeners.ScanListener;
import fr.utbm.gl52.model.Model;

public class ExtractedDataPanel extends JPanel implements ScanListener {

	private static final long serialVersionUID = -9133411730442747891L;

	private static final int HEIGHT = 650;
	private static final int WIDTH = 460;
	
	private List<DocumentListener> documentListeners = new ArrayList<DocumentListener>();

	private Document document;
	
	DateFormat f = new SimpleDateFormat("MM/DD/YYYY");
	private PTextField commentFld;	
	private PTextField clientfirstnameFld;	
	private PTextField clientlastnameFld;
	private PTextField clientcityFld;
	private PTextField clientcountryFld;
	private PTextField clientstreetFld;
	private PTextField clientpostalcodeFld;
	private PTextField storecityFld;
	private PTextField storepostalcodeFld;
	private PTextField storecountryFld;
	private PTextField storestreetFld;
	private PTextField storewebsiteFld;
	private JFormattedTextField dateFld;
	private PTextField companynameFld;
	private PTextField companystreetFld;
	private PTextField companycountryFld;
	private PTextField companycityFld;
	private PTextField companypostalcodeFld;
	private PTextField billNumber;
	private PTextField totalTTCFld;
	private PTextField totalFld;
	private PComboBox totalCb;
	private PComboBox totalTTCCb;
	
	private PButton saveBtn;
	private PButton delBtn;
	private PButton cancelBtn;
	private ArticleTableModel articleTable;

	public ExtractedDataPanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Client infos
		clientfirstnameFld = new PTextField();
		clientlastnameFld = new PTextField();
		clientcityFld = new PTextField();
		clientcountryFld = new PTextField();
		clientstreetFld = new PTextField(250);
		clientpostalcodeFld = new PTextField();
		Container clientPane = new Container();
		clientPane.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(2,2,2,2);
		gc.gridx = 0;
		gc.gridy = 0;	
		clientPane.add(new PLabel("Name"), gc);
		
		gc.gridx = 1;
		gc.gridwidth = 2; 
		clientPane.add(clientlastnameFld, gc);
		
		gc.gridx = 3;
		gc.gridwidth = 1;
		clientPane.add(new PLabel("First name"), gc);
		
		gc.gridx = 4;
		gc.gridwidth = 2;
		clientPane.add(clientfirstnameFld,gc);
		
		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;
		clientPane.add(new PLabel("Street"),gc);
		
		gc.gridx = 1;
		gc.gridwidth = 5;
		clientPane.add(clientstreetFld, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		clientPane.add(new PLabel("City"), gc);
		
		gc.gridx = 1;
		clientPane.add(clientcityFld, gc);
		
		gc.gridx = 2;
		clientPane.add(new PLabel("Code"), gc);
		
		gc.gridx = 3;
		clientPane.add(clientpostalcodeFld, gc);
		
		gc.gridx = 4;
		clientPane.add(new PLabel("Country"), gc);
		
		gc.gridx = 5;
		clientPane.add(clientcountryFld, gc);

		clientPane.setMinimumSize(new Dimension(getWidth(), 20));
		//clientPane.setMaximumSize(new Dimension(getWidth(), 150));
		clientPane.setPreferredSize(new Dimension(getWidth(), 180));
		PPane gclientPane = new PPane("Client informations", 200, true);
		gclientPane.add(clientPane, BorderLayout.CENTER);
		//gclientPane.setPreferredSize(new Dimension(this.getWidth(), 15));
		add(gclientPane);

		// Seller infos
		companynameFld = new PTextField();
		companystreetFld = new PTextField();
		companycountryFld = new PTextField();
		companycityFld = new PTextField();
		companypostalcodeFld = new PTextField();
		storewebsiteFld = new PTextField();
		JPanel companyPane = new JPanel(new GridBagLayout());
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		companyPane.add(new PLabel("Name"), gc);
		
		gc.gridx = 1;
		gc.gridwidth = 2;
		companyPane.add(companynameFld, gc);
		
		gc.gridx = 3;
		gc.gridwidth = 1;
		companyPane.add(new PLabel("Site"), gc);
		
		gc.gridx = 4;
		gc.gridwidth = 2;
		companyPane.add(storewebsiteFld, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		companyPane.add(new PLabel("Street"), gc);
		
		gc.gridx = 1;
		gc.gridwidth = 5;
		companyPane.add(companystreetFld, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		companyPane.add(new PLabel("City"), gc);
		
		gc.gridx = 1;
		companyPane.add(companycityFld, gc);
		
		gc.gridx = 2;
		companyPane.add(new PLabel("Code"), gc);
		
		gc.gridx = 3;
		companyPane.add(companypostalcodeFld, gc);
		
		gc.gridx = 4;
		companyPane.add(new PLabel("Country"), gc);
		
		gc.gridx = 5;
		companyPane.add(companycountryFld, gc);
		
		companyPane.setMinimumSize(new Dimension(this.getWidth(), 15));
		companyPane.setMaximumSize(new Dimension(this.getWidth(), 190));
		//companyPane.setPreferredSize(new Dimension(this.getWidth(), 15));
		PPane gcompanyPane = new PPane("Seller informations", 190, true);
		gcompanyPane.add(companyPane, BorderLayout.CENTER);
		add(gcompanyPane);
		
		// Store informations
		storecityFld = new PTextField();
		storecountryFld = new PTextField();
		storestreetFld = new PTextField();
		storepostalcodeFld = new PTextField();
		JPanel storePane = new JPanel(new GridBagLayout());
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		storePane.add(new PLabel("Street"), gc);
		
		gc.gridx = 1;
		gc.gridwidth = 5;
		storePane.add(storestreetFld, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		storePane.add(new PLabel("City"), gc);
		
		gc.gridx = 1;
		storePane.add(storecityFld, gc);
		
		gc.gridx = 2;
		storePane.add(new PLabel("Code"), gc);
		
		gc.gridx = 3;
		storePane.add(storepostalcodeFld, gc);
		
		gc.gridx = 4;
		storePane.add(new PLabel("Country"), gc);
		
		gc.gridx = 5;
		storePane.add(storecountryFld, gc);
		
		storePane.setMinimumSize(new Dimension(this.getWidth(), 15));
		storePane.setMaximumSize(new Dimension(this.getWidth(), 140));
		storePane.setPreferredSize(new Dimension(this.getWidth(), 15));
		PPane gstorePane = new PPane("Store location", 140, false);
		gstorePane.add(storePane, BorderLayout.CENTER);
		add(gstorePane);

		// General infos
		try {
			MaskFormatter mf = new MaskFormatter("__/__/____");
			dateFld = new JFormattedTextField(mf);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		dateFld.setPreferredSize(new Dimension(70, 20));
		dateFld.setMinimumSize(new Dimension(70, 20));
		commentFld = new PTextField();
		billNumber = new PTextField();
		commentFld.setPreferredSize(new Dimension(365, 40));
		commentFld.setMinimumSize(new Dimension(365, 20));
		JPanel datePane = new JPanel(new GridBagLayout());
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		datePane.add(new PLabel("Date"), gc);
		
		gc.gridx = 1;
		datePane.add(dateFld, gc);
		
		gc.gridx = 2;
		datePane.add(new PLabel("Number", 70), gc);

		gc.gridx = 3;
		datePane.add(billNumber, gc);
		
		gc.gridy = 1;
		gc.gridx = 0;
		datePane.add(new PLabel("Comments", 70), gc);
		
		gc.gridx = 1;
		gc.gridwidth = 4;
		datePane.add(commentFld, gc);

		datePane.setMinimumSize(new Dimension(this.getWidth(), 15));
		datePane.setMaximumSize(new Dimension(this.getWidth(), 140));
		//datePane.setPreferredSize(new Dimension(this.getWidth(), 15));
		PPane ggalPane = new PPane("General informations", 140, false);
		ggalPane.add(datePane, BorderLayout.CENTER);
		add(ggalPane);
		
		// Articles table
		articleTable = new ArticleTableModel();
		add(articleTable);
		
		PPane garticlesPane = new PPane("List of products", 120, true);
		garticlesPane.desactivateButton();
		garticlesPane.add(articleTable, BorderLayout.CENTER);
		add(garticlesPane);
		
		// Total
		totalFld = new PTextField(70);
		totalFld.setEnabled(false);
		totalCb = new PComboBox(Currency.values());
		totalTTCFld = new PTextField(70);
		totalTTCFld.setEnabled(false);
		totalTTCCb = new PComboBox(Currency.values());
		totalTTCCb.setPreferredSize(new Dimension(60, 20));
		totalCb.setPreferredSize(new Dimension(60, 20));
		
		JPanel totalPane = new JPanel();
		totalPane.setLayout(new GridBagLayout());
		
		gc.gridx = 2;
		gc.gridy = 0;
		gc.gridheight = 1;
		gc.gridwidth = 2;
		PLabel totalHTlbl = new PLabel("Total excluding taxes");
		totalHTlbl.setPreferredSize(new Dimension(150,20));
		totalPane.add(totalHTlbl, gc);
		
		gc.gridx = 4;
		gc.gridwidth = 1;
		totalPane.add(totalFld, gc);
		
		gc.gridx = 5;
		totalPane.add(totalCb, gc);
		
		gc.gridy = 1;
		gc.gridx = 2;
		gc.gridwidth = 2;
		PLabel totalTTClbl = new PLabel("Total including taxes");
		totalHTlbl.setPreferredSize(new Dimension(150,20));
		totalPane.add(totalTTClbl, gc);
		
		gc.gridx = 4;
		gc.gridwidth = 1;
		totalPane.add(totalTTCFld, gc);
		
		gc.gridx = 5;
		totalPane.add(totalTTCCb, gc);
		add(totalPane);

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
					showDocument(document, false);
				}
		});

		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPane.add(saveBtn);
		btnPane.add(delBtn);
		btnPane.add(cancelBtn);
		add(btnPane);
		
		activatePanel(false);
	}
	
	private void activatePanel(boolean isActivated){
		activateButtons(isActivated, isActivated, isActivated);
		clientfirstnameFld.setEditable(isActivated);
		clientlastnameFld.setEditable(isActivated);
		clientstreetFld.setEditable(isActivated);
		clientcountryFld.setEditable(isActivated);
		clientcityFld.setEditable(isActivated);
		clientpostalcodeFld.setEditable(isActivated);
		storecityFld.setEditable(isActivated);
		storecountryFld.setEditable(isActivated);
		storewebsiteFld.setEditable(isActivated);
		storestreetFld.setEditable(isActivated);
		storepostalcodeFld.setEditable(isActivated);
		commentFld.setEditable(isActivated);
		dateFld.setEditable(isActivated);
		billNumber.setEditable(isActivated);
		companynameFld.setEditable(isActivated);
		companystreetFld.setEditable(isActivated);
		companycountryFld.setEditable(isActivated);
		companycityFld.setEditable(isActivated);
		companypostalcodeFld.setEditable(isActivated);
		companynameFld.setEditable(isActivated);
		totalFld.setEditable(isActivated);
		totalTTCFld.setEditable(isActivated);
		totalCb.setEnabled(isActivated);
		totalTTCCb.setEnabled(isActivated);
		articleTable.setEditable(isActivated);
	}
	
	private void activateButtons(boolean save, boolean del, boolean cancel) {
		this.saveBtn.setEnabled(save);
		this.delBtn.setEnabled(del);
		this.cancelBtn.setEnabled(cancel);
	}
	
	public void showDocument(Document document, boolean isModifiedDocument){
		  this.document = document;
		  DocumentInfo infos;
		  if(isModifiedDocument){
			  infos = this.document.getModifiedInfos();   
		  } else {
			  infos = this.document.getInitialInfos(); 
		  }
		  
		  clientfirstnameFld.setText(infos.getClient().getFirstName());
		  clientlastnameFld.setText(infos.getClient().getLastName());
		  clientstreetFld.setText(infos.getClient().getAddress().getStreet());
		  clientcityFld.setText(infos.getClient().getAddress().getCity());
		  clientcountryFld.setText(infos.getClient().getAddress().getCountry());
		  clientpostalcodeFld.setText(String.valueOf(infos.getClient().getAddress().getPostalCode()));
		  storecityFld.setText(infos.getStore().getCity());
		  storecountryFld.setText(infos.getStore().getCountry());
		  storestreetFld.setText(infos.getStore().getStreet());
		  storepostalcodeFld.setText(String.valueOf(infos.getStore().getPostalCode()));
		  storewebsiteFld.setText(infos.getSupplier().getWebsite());
		  companynameFld.setText(infos.getSupplier().getName());
		  companycityFld.setText(infos.getSupplier().getAddress().getCity());
		  companystreetFld.setText(infos.getSupplier().getAddress().getStreet());
		  companycountryFld.setText(infos.getSupplier().getAddress().getCountry());
		  companypostalcodeFld.setText(String.valueOf(infos.getSupplier().getAddress().getPostalCode()));
		  dateFld.setText(f.format(infos.getDate()));
		  billNumber.setText(String.valueOf(infos.getFactureNumber()));
		  totalFld.setText(String.valueOf(infos.getTotal().getPriceExcludingTaxes()));
		  totalTTCFld.setText(String.valueOf(infos.getTotal().getPriceIncludingTaxes()));
		  totalCb.setSelectedItem(infos.getTotal().getCurrency());
		  totalTTCCb.setSelectedItem(infos.getTotal().getCurrency());
		  articleTable.update(infos.getProducts());
		  articleTable.repaint();
		  activatePanel(true);
	}

	public void updateDocument(){
		DocumentInfo infos = document.getInitialInfos();
		infos.getClient().setFirstName(clientfirstnameFld.getText());
		infos.getClient().setLastName(clientlastnameFld.getText());
		infos.getClient().getAddress().setCity(clientcityFld.getText());
		infos.getClient().getAddress().setCountry(clientcountryFld.getText());
		infos.getClient().getAddress().setStreet(clientstreetFld.getText());
		infos.getClient().getAddress().setPostalCode(Integer.parseInt(clientpostalcodeFld.getText()));
		infos.getSupplier().setName(companynameFld.getText());
		infos.getSupplier().getAddress().setCity(companycityFld.getText());
		infos.getSupplier().getAddress().setCountry(companycountryFld.getText());
		infos.getSupplier().getAddress().setStreet(companystreetFld.getText());
		infos.getSupplier().getAddress().setPostalCode(Integer.parseInt(companypostalcodeFld.getText()));
		infos.getStore().setCity(storecityFld.getText());
		infos.getStore().setCountry(storecountryFld.getText());
		infos.getStore().setStreet(storestreetFld.getText());
		infos.getStore().setPostalCode(Integer.parseInt(storepostalcodeFld.getText()));
		try {
			infos.setDate(f.parse(dateFld.getText()));
		} catch (ParseException e) {
			System.out.println("Format de date incorrect : le texte ne peut être converti en object Date");
			e.printStackTrace();
		}
		Currency currency = (Currency) totalCb.getSelectedItem();
		infos.setTotal(new Price(Float.parseFloat(totalTTCFld.getText()), Float.parseFloat(totalFld.getText()), currency));		
		infos.setProducts(articleTable.getProducts());
		infos.getSupplier().setWebsite(storewebsiteFld.getText());
		infos.setFactureNumber(billNumber.getText());
	}
	
	public void addDocumentListener(DocumentListener listener) {
		documentListeners.add(listener);
	}

	public void removeDocumentListener(DocumentListener listener) {
		documentListeners.remove(listener);
	}

	private void fireSaveDoc() {
		for (DocumentListener elt : documentListeners) {
			if(document != null){
				if(elt.saveDocument(document)){
					JOptionPane.showMessageDialog(this, "Document has been correctly saved.");
				} else {
					JOptionPane.showMessageDialog(this, "Document couldn't be saved.");
				}		
			} else {
				System.out.println("Pas de document à enregistrer");
			}
		}
	}

	private void fireDeleteDoc() {
		for (DocumentListener elt : documentListeners) {
			if(document != null){
				if(elt.deleteDocument(document.getId())){
					JOptionPane.showMessageDialog(this, "Document has been correctly deleted.");
				} else {
					JOptionPane.showMessageDialog(this, "Document couldn't be deleted.");
				}	
			} else {
				System.out.println("Pas de document à supprimer");
			}
		}
	}

	private void fireCancelModifsDoc() {
		for (DocumentListener elt : documentListeners) {
			if(document != null){
				elt.cancelModifsDocument(document.getId());
				showDocument(document, false);
			} else {
				System.out.println("Pas de document sur lequel opérer");
			}
		}
	}

	public void receiveDocument(Document doc){
		showDocument(doc, false);
	}
	
	@Override
	public void launchScan(File receivedFile, Model model) {
		// ne rien faire
	}
}
