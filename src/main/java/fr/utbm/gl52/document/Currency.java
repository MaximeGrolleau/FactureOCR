package fr.utbm.gl52.document;

public enum Currency {
	
	DEFLT("DEFAULT"),EUR("EURO"), GPB("Pound"), USD("US DOLLAR"), CAD("CANADIAN DOLLAR"), AUD("AUSTRALIAN DOLLAR"), INR("INDIAN RUPEE"), AED("DIRHAM");
	
	private final String name;
	
	private Currency(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
