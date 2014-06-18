package fr.utbm.gl52.document;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum DocumentType {

	/**
	 * 
	 */
	BILL("BILL"),
	/**
	 * 
	 */
	RECEIPT("RECEIPT");
	
	private final String name;
	
	private DocumentType(String name){
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	
}
