package fr.utbm.gl52.model;

public enum ContentType {
	
	CLIENT_INFO_TELEPHONE, SELLER_INFO_ADRESSE, SELLER_INFO, DOCUMENT_INFO, PRODUCTS;
	
	private ContentType(){}
	
	public static ContentType get(String name){
		return ContentType.valueOf(name);
	}

}
