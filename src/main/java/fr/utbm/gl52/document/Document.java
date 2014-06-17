package fr.utbm.gl52.document;

import java.io.File;

public class Document {

	private final DocumentType type;
	private final File attachedFile;
	private final DocumentInfo initialInfos;
	private DocumentInfo modifiedInfos;
	
	public Document(DocumentType type, File file, DocumentInfo initInfos){
		this.type = type;
		this.attachedFile = file;
		this.initialInfos = initInfos;
	}

	public DocumentType getType() {
		return type;
	}
 
	public File getAttachedFile() {
		return attachedFile;
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
}
