package fr.utbm.gl52.gui.component;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ArticleTable extends JPanel{

	private static final long serialVersionUID = 7694873324134768876L;
	
	public ArticleTable(){
		
		String[] header = {"Ref.", "Desc.", "Qty","Price"};
		Object[][] data = new Object[4][10]; 
		
		JTable table = new JTable(data, header);
		table.setPreferredSize(new Dimension(getWidth(), 300));
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}

}
