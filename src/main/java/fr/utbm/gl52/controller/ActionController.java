package fr.utbm.gl52.controller;

import org.hibernate.Session;

import fr.utbm.gl52.database.HibernateUtil;
import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.gui.listeners.DocumentListener;

public class ActionController implements DocumentListener {
	
	@Override
	public boolean saveDocument(Document doc) {
		Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.saveOrUpdate(doc);
        session.getTransaction().commit();
        return true;
	}

	@Override
	public boolean deleteDocument(int ID) {
		Document.deleteDocument(ID);
		return false;
	}

	@Override
	public void cancelModifsDocument(int ID) {
	}

	@Override
	public void sendDocument(Document documentSelected) {
		// ne fais rien
		
	}

}
