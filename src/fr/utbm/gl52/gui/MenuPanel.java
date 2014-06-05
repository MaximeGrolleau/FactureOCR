package fr.utbm.gl52.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import fr.utbm.gl52.gui.component.PButton;
import fr.utbm.gl52.gui.listeners.MenuListener;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -3951101473129489983L;
	
	private List<MenuListener> menuListeners = new ArrayList<MenuListener>();
	
	public MenuPanel(){
		setPreferredSize(new Dimension(800, 35));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		PButton btnOpen = new PButton("Open document from DB",
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fireOpenWindowSearchDocument();
					}
				});
		add(btnOpen);
		PButton btnStats = new PButton("Show statistics", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireOpenWindowShowStats();
			}
		});
		add(btnStats);
	}
	
	public void addMenuListener(MenuListener listener) {
		menuListeners.add(listener);
	}

	public void removeMenuListener(MenuListener listener) {
		menuListeners.remove(listener);
	}

	public void fireOpenWindowSearchDocument() {
		for (MenuListener elt : menuListeners) {
			elt.searchDocumentFromDb();
		}
	}

	public void fireOpenWindowShowStats() {
		for (MenuListener elt : menuListeners) {
			elt.showStats();
		}
	}
}
