package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;

import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.component.PComboBox;

public class LoadDocumentFrame extends JFrame {

	private static final long serialVersionUID = -5695950788241214128L;
	private int docID = -1;
	
	public LoadDocumentFrame() {
		
		setLayout(new BorderLayout());
		PComboBox filter = new PComboBox(new String[]{"Facture", "Ticket de caisse"});
		add(filter, BorderLayout.NORTH);

		String[] header = { "Name", "Client", "Company", "Date" };
		String[][] data = new String[4][10];
		JTable tableDocuments = new JTable(data, header);
		add(tableDocuments, BorderLayout.CENTER);
		
		PButton btnLoad = new PButton("Load", new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		add(btnLoad, BorderLayout.SOUTH);

	}

	public int showWindow() {
		setVisible(true);
		return docID;
	}

}
