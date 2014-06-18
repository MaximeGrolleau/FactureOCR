package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.hibernate.Query;

import fr.utbm.gl52.database.HibernateUtil;
import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.document.DocumentInfo;
import fr.utbm.gl52.document.DocumentType;
import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;
import fr.utbm.gl52.gui.listeners.DocumentListener;
import fr.utbm.gl52.gui.listeners.ScanListener;

public class LoadDocumentFrame extends JFrame {

	private static final long serialVersionUID = -5695950788241214128L;
	private List<Document> docs = null;
	private Document documentSelected = null;
	private List<ScanListener> scanListeners = new ArrayList<ScanListener>();
	private JTable tableDocuments;
	
	public LoadDocumentFrame() {
		
		setSize(new Dimension(400, 400));
		setLayout(new BorderLayout());
		final PComboBox filter = new PComboBox(DocumentType.values());
		filter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LoadDocumentFrame.this.docs = getAllDocuments((DocumentType) filter.getSelectedItem());
				if(LoadDocumentFrame.this.docs.isEmpty()){
					JOptionPane.showMessageDialog(getContentPane(), "No " +filter.getSelectedItem()+ " in database.");
				} else {
					updateTable();					
				}
			}
		});
		add(filter, BorderLayout.NORTH);

		this.docs = getAllDocuments((DocumentType) filter.getSelectedItem());
		String[] header = {"Client", "Company", "Date" };
		if(this.docs.size() == 0){
			//TODO Qu'es ce qu'on fait :) 
		}
			
			String[][] data = new String[this.docs.size()][3];
			this.tableDocuments = new JTable(data, header);
			updateTable();
			add(this.tableDocuments, BorderLayout.CENTER);

		
		
		
		PButton btnLoad = new PButton("Load", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LoadDocumentFrame.this.documentSelected != null){
					fireDocumentSelected();
					dispose();
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "No document selected.");
				}
			}
		});
		add(btnLoad, BorderLayout.SOUTH);
		setVisible(true);
		
	}

	private void fireDocumentSelected() {
		for(ScanListener elt : this.scanListeners){
			elt.receiveDocument(this.documentSelected);
		}
	}
	
	public void updateTable(){
		for(int i = 0 ; i < this.docs.size(); i ++){
			DocumentInfo infos = this.docs.get(i).getInitialInfos();
			if(infos!= null){
				this.tableDocuments.setValueAt(String.valueOf(infos.getFactureNumber()), i, 0);
				this.tableDocuments.setValueAt(infos.getClient().getLastName(),i,1);
				this.tableDocuments.setValueAt(infos.getSupplier().getName(),i,2);
				this.tableDocuments.setValueAt(infos.getDate().toString(),i,3);
			} else {
				System.out.println("document.documentInfo du document chargé nul");
			}
		}
	}
	
	public List<Document> getAllDocuments(DocumentType type){
		Query q = HibernateUtil.getSession().createQuery("From Document where type = :doctype"); //$NON-NLS-1$
		q.setParameter("doctype", type); //$NON-NLS-1$
		List<Document> resultList = q.list();
		System.out.println(resultList.size());
		return resultList;
	}

	public void addDocumentListener(ScanListener listener) {
		this.scanListeners.add(listener);
	}

}
