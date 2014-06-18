package fr.utbm.gl52.gui.listeners;

import java.io.File;

import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.model.Model;

public interface ScanListener {

	public void receiveDocument(Document doc);

	public void launchScan(File receivedFile, Model model);

}
