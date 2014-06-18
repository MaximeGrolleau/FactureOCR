package fr.utbm.gl52.gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.utbm.gl52.document.Product;

public class ArticleTableModel extends JPanel implements TableModel {

	private static final long serialVersionUID = 7461212547930675721L;

	private List<TableModelListener> tableModelListener = new ArrayList<TableModelListener>();
	private JTable table;
	private String[] header= { "Ref.", "Desc.", "Qty", "Price excl. taxes", "Price incl. taxes"};
	Object[][] data = null;

	public ArticleTableModel() {
		data = new Object[5][10];

		table = new JTable(data, header);
		table.setPreferredSize(new Dimension(getWidth(), 300));
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	
	}
	
	public ArticleTableModel(List<Product> products) {
		data = new Object[5][products.size()];

		for(int i = 0; i<products.size(); i++){
			data[0][i] = products.get(i).getReference();
			data[1][i] = products.get(i).getName();
			data[2][i] = products.get(i).getQuantity();
			data[3][i] = products.get(i).getTaxExclPrice();
			data[4][i] = products.get(i).getTaxInclPrice();		
		}
		
		table = new JTable(data, header);
		table.setPreferredSize(new Dimension(getWidth(), 300));
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	
	}

	public void addTableModelListener(TableModelListener listener) {
		tableModelListener.add(listener);
	}

	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getColumnCount() {
		return header.length;
	}

	public String getColumnName(int index) {
		return header[index];
	}

	public int getRowCount() {
		return data[0].length;
	}

	public Object getValueAt(int xindex, int yindex) {
		return data[xindex][yindex];
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
	}

	public void removeTableModelListener(TableModelListener listener) {
		tableModelListener.remove(listener);
	}

	public void setValueAt(Object cellContent, int xindex, int yindex) {
		data[xindex][yindex] = cellContent;
	}

	public void setEditable(boolean isEditable){
		this.table.setEnabled(isEditable);
		this.table.setBackground(Color.LIGHT_GRAY);
	}
}
