package fr.utbm.gl52.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import fr.utbm.gl52.controller.ActionController;
import fr.utbm.gl52.gui.listeners.MenuListener;
import fr.utbm.gl52.ocr.TextExtractor;

public class AppFrame extends JFrame implements MenuListener {
	
	private static final long serialVersionUID = -702732361996190737L;
	private ActionController controller = new ActionController();

	public AppFrame(TextExtractor te){
		setTitle("FactureOCR");
		setSize(new Dimension(850,700));
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		
		MenuPanel menuPanel = new MenuPanel();
		menuPanel.addMenuListener(this);

		DocumentPanel documentPanel = new DocumentPanel();
		documentPanel.addScanListener(te);

		ExtractedDataPanel dataPanel = new ExtractedDataPanel();
		dataPanel.addDocumentListener(controller);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		content.add(BorderLayout.NORTH, menuPanel);
		splitPane.add(documentPanel);
		splitPane.add(dataPanel);
		content.add(BorderLayout.CENTER, splitPane);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void searchDocumentFromDb() {
		LoadDocumentFrame searchFrame = new LoadDocumentFrame();
		searchFrame.setAlwaysOnTop(true);
		searchFrame.setPreferredSize(new Dimension(100, 100));
		searchFrame.setLocationRelativeTo(this);
		searchFrame.setVisible(true);
	}

	public void showStats() {
		StatisticFrame statFrame = new StatisticFrame();
		statFrame.setLocationRelativeTo(this);
		statFrame.setPreferredSize(new Dimension(300, 300));
		statFrame.setVisible(true);
	}

}
