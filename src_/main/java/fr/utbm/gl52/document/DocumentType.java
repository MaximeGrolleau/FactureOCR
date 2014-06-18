package fr.utbm.gl52.document;

public enum DocumentType {

	BILL("BILL"), RECEIPT("RECEIPT");
	
	private final String name;
	
	private DocumentType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
