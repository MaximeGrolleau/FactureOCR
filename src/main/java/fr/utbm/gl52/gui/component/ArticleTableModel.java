package fr.utbm.gl52.gui.component;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.utbm.gl52.document.Product;

public class ArticleTableModel extends JScrollPane implements TableModel {

	private static final long serialVersionUID = 7461212547930675721L;

	private List<TableModelListener> tableModelListener = new ArrayList<TableModelListener>();
	private JTable table;
	private String[] header= { "Ref.", "Desc.", "Qty", "Price excl. taxes", "Price incl. taxes"};
	Object[][] data = null;

	public ArticleTableModel() {
		data = new Object[23][5];

		table = new JTable(data, header);
		JPanel contentPane = new JPanel(new BorderLayout());
		
		setLayout(new ScrollPaneLayout());
		contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
		contentPane.add(table, BorderLayout.CENTER);
		setViewportView(contentPane);
	
	}
	
	public void update(List<Product> products) {
		//data = new String[products.size()][5];

		for(int i = 0; i<products.size(); i++){table.setValueAt(products.get(i).getReference(), i, 0);
			table.setValueAt(products.get(i).getName(), i, 1);
			table.setValueAt(String.valueOf(products.get(i).getQuantity()), i, 2);
			table.setValueAt(String.valueOf(products.get(i).getPrice().getPriceExcludingTaxes()), i, 3);
			table.setValueAt(String.valueOf(products.get(i).getPrice().getPriceIncludingTaxes()), i, 4);
		}
		
		table.repaint();
	}
	
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<Product>();
		
		for(int i = 0; i<data[0].length; i++){
			Product product = new Product(String.valueOf(data[i][1]), 
					String.valueOf(data[i][0]), 
					Float.parseFloat(String.valueOf(data[i][4])), 
					Float.parseFloat(String.valueOf(data[i][3])), 
					null, 
					Integer.valueOf((String.valueOf(data[i][2]))));
			products.add(product);
		}
		return products;
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
		//this.table.setBackground(Color.LIGHT_GRAY);
	}
}
