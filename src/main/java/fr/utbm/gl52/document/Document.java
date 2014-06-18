package fr.utbm.gl52.document;

import java.io.File;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.utbm.gl52.database.HibernateUtil;

@Entity
public class Document {

	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private DocumentType type;
    @Lob
	private File attachedFileData;
	private String attachedFileName;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public DocumentInfo initialInfos;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private DocumentInfo modifiedInfos;
	
	public Document(){
		
	}
	
	/**
	 * @param type
	 * @param file
	 * @param name
	 * @param initInfos
	 */
	public Document(DocumentType type, File file, String name, DocumentInfo initInfos){
		this.type = type;
		this.attachedFileData = file;
		this.attachedFileName = name;
		this.initialInfos = initInfos;
	}

	public DocumentType getType() {
		return type;
	}

	public DocumentInfo getInitialInfos() {
		return initialInfos;
	}

	public DocumentInfo getModifiedInfos() {
		return modifiedInfos;
	}

	public void setModifiedInfos(DocumentInfo modifiedInfos) {
		this.modifiedInfos = modifiedInfos;
	}
	
	public int getId(){
		return id;
	}

	/**
	 * @return the attachedFileName
	 */
	public String getAttachedFileName() {
		return attachedFileName;
	}

	/**
	 * @return the attachedFileData
	 */
	public File getAttachedFileData() {
		return attachedFileData;
	}
	
	/**
	 * @param type
	 * @return
	 */
	public List<Document> getAllDocuments(DocumentType type){
		
        Query q = HibernateUtil.getSession().createQuery("From Document where type = :doctype"); //$NON-NLS-1$
        q.setParameter("doctype", type); //$NON-NLS-1$
		List<Document> resultList = q.list();
        return resultList;
	}
	
	public void deleteDocument(int id){
		
		Query query = HibernateUtil.getSession().createQuery("delete Document where id = :id"); //$NON-NLS-1$
		query.setParameter("id", id); //$NON-NLS-1$
		int result = query.executeUpdate();
	}
	
	public Document getDocument(int id){
		
        Query q = HibernateUtil.getSession().createQuery("From Document where id = :id"); //$NON-NLS-1$
        q.setParameter("id", id); //$NON-NLS-1$
		@SuppressWarnings("unchecked")
		List<Document> resultList = q.list();
		if(resultList.size() != 0)
			return resultList.get(0);
		return null;
	}
}


