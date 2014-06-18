package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import fr.utbm.gl52.controller.ActionController;
import fr.utbm.gl52.gui.listeners.MenuListener;
import fr.utbm.gl52.model.Model;
import fr.utbm.gl52.ocr.TextExtractor;

public class AppFrame extends JFrame implements MenuListener {
	
	private static final long serialVersionUID = -702732361996190737L;
	private ActionController controller = new ActionController();
	private ExtractedDataPanel dataPanel = new ExtractedDataPanel();
	
	public AppFrame(TextExtractor te, List<Model> models){
		setTitle("FactureOCR");
		setSize(new Dimension(860,700));
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.addMenuListener(this);

		DocumentPanel documentPanel = new DocumentPanel(models);
		documentPanel.addScanListener(te);

		
		dataPanel.addDocumentListener(controller);
		
		te.addScanListener(dataPanel);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setEnabled(false);
		content.add(BorderLayout.NORTH, menuPanel);
		splitPane.add(documentPanel);
		splitPane.add(dataPanel);
		content.add(BorderLayout.CENTER, splitPane);
		
		pack();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void searchDocumentFromDb() {
		LoadDocumentFrame searchFrame = new LoadDocumentFrame();
		searchFrame.setAlwaysOnTop(true);
		searchFrame.setLocationRelativeTo(this);
		searchFrame.addDocumentListener(dataPanel);
	}

	public void showStats() {
		StatisticFrame statFrame = new StatisticFrame();
		statFrame.setLocationRelativeTo(this);
		statFrame.setPreferredSize(new Dimension(300, 300));
		statFrame.setVisible(true);
	}

}
