package fr.utbm.gl52.document;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum Currency {
	
	EUR("EURO"), GPB("Pound"), USD("US DOLLAR"), CAD("CANADIAN DOLLAR"), AUD("AUSTRALIAN DOLLAR"), INR("INDIAN RUPEE"), AED("DIRHAM");
	
	private String Name;
	
	private Currency(String name){
		this.Name  = name;
	}
	
}
