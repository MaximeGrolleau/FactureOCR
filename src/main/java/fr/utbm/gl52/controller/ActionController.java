package fr.utbm.gl52.controller;

import org.hibernate.Session;

import fr.utbm.gl52.database.HibernateUtil;
import fr.utbm.gl52.document.Document;
import fr.utbm.gl52.gui.listeners.DocumentListener;

public class ActionController implements DocumentListener {
	
	public boolean saveDocument(Document doc) {
		//TODO maxime save document -> exception chez moi
		// je ferme la session alors qu'il faut la laisser ouverte ?
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(doc);
        session.getTransaction().commit();
        session.close();
        return true;
	}

	public boolean deleteDocument(int ID) {
		//TODO maxime delete document
		return false;
	}

	public void cancelModifsDocument(int ID) {
	}

	@Override
	public void sendDocument(Document documentSelected) {
		// ne fais rien
		
	}

}
