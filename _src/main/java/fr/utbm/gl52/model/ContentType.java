package fr.utbm.gl52.model;

public enum ContentType {
	
	CLIENT_INFO_TELEPHONE, CLIENT_INFO, SELLER_INFO_ADRESSE, SELLER_INFO, DOCUMENT_INFO, PRODUCTS;
	
	private ContentType(){}
	
	public static ContentType get(String name){
		try{
			return ContentType.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException iae) {
			System.out.println("Pas de type " + name + " trouvé dans la classe enum ContentType");
	        return null;
	    }
	}

}
