package fr.utbm.gl52.gui.listeners;


import fr.utbm.gl52.document.Document;

public interface DocumentListener {

	public boolean saveDocument(Document document);

	public boolean deleteDocument(int ID);

	public void cancelModifsDocument(int ID);

	public void sendDocument(Document documentSelected);

}
