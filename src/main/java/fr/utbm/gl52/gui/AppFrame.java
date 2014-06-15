package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import fr.utbm.gl52.controller.ActionController;

public class AppFrame extends JFrame {
	
	private static final long serialVersionUID = -702732361996190737L;
	private ActionController controller = new ActionController();

	public AppFrame(){
		setTitle("FactureOCR");
		setSize(new Dimension(800,700));
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		
		MenuPanel menuPanel = new MenuPanel();
		DocumentPanel documentPanel = new DocumentPanel();
		ExtractedDataPanel dataPanel = new ExtractedDataPanel();
		dataPanel.addFileListener(documentPanel);
		dataPanel.addDocumentListener(controller);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		content.add(BorderLayout.NORTH, menuPanel);
		splitPane.add(dataPanel);
		splitPane.add(documentPanel);
		content.add(splitPane);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
