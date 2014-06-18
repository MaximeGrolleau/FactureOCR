package fr.utbm.gl52.document;

public enum DocumentType {

	DFLT("Select a type ..."),
	BILL("Bill"),
	RECEIPT("Receipt");
	
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

	static public String[] getList(){
		String[] names = new String[]{
				DFLT.getName(), BILL.getName(), RECEIPT.getName()
		};
		return names;
	}
	
	static public DocumentType getDocumentType(String name){
		switch (name){
		case "Bill":
			return BILL;
		case "Receipt":
			return RECEIPT;
		default:
			return DFLT;
		}
	}
	
}
