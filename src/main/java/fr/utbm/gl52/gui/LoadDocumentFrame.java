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
				docs = getAllDocuments((DocumentType) filter.getSelectedItem());
				if(docs.isEmpty()){
					JOptionPane.showMessageDialog(getContentPane(), "No " +filter.getSelectedItem()+ " in database.");
				} else {
					updateTable();					
				}
			}
		});
		add(filter, BorderLayout.NORTH);

		docs = getAllDocuments((DocumentType) filter.getSelectedItem());
		String[] header = {"Client", "Company", "Date" };
		String[][] data = new String[docs.size()][3];
		tableDocuments = new JTable(data, header);
		updateTable();
		
		add(tableDocuments, BorderLayout.CENTER);
		
		PButton btnLoad = new PButton("Load", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(documentSelected != null){
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
		for(ScanListener elt : scanListeners){
			elt.receiveDocument(documentSelected);
		}
	}
	
	public void updateTable(){
		for(int i = 0 ; i < docs.size(); i ++){
			DocumentInfo infos = docs.get(i).getInitialInfos();
			if(infos!= null){
				tableDocuments.setValueAt(docs.get(i).getInitialInfos().getClient().getLastName(),i,1);
				tableDocuments.setValueAt(docs.get(i).getInitialInfos().getSupplier().getName(),i,2);
				tableDocuments.setValueAt(docs.get(i).getInitialInfos().getDate().toString(),i,3);
			}
		}
	}
	
	public List<Document> getAllDocuments(DocumentType type){
		// TODO maxime getDocument From Type
		Query q = HibernateUtil.getSession().createQuery("From Document where type = :doctype"); //$NON-NLS-1$
		q.setParameter("doctype", type); //$NON-NLS-1$
		List<Document> resultList = q.list();
		return resultList;
	}

	public void addDocumentListener(ScanListener listener) {
		scanListeners.add(listener);
	}

}
