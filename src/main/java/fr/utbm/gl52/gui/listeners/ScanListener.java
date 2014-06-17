package fr.utbm.gl52.gui.listeners;

import fr.utbm.gl52.document.Document;

public interface ScanListener {

	public void receiveDocument(Document doc);
	
	public void launchScan();

}
