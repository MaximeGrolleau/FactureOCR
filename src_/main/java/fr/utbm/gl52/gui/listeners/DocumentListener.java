package fr.utbm.gl52.gui.listeners;

import fr.utbm.gl52.document.Document;

public interface DocumentListener {

	public void saveDocument(Document document);

	public void deleteDocument(int ID);

	public void cancelModifsDocument(int ID);

}
