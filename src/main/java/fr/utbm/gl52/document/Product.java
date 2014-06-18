package fr.utbm.gl52.document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Product {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String reference;
	private Price price;
	private int quantity;
	
	public Product(String name, String reference, float taxInclPrice, float taxExclPrice, Currency curr, int quantity){
		this.setName(name);
		this.setReference(reference);
		this.setPrice(new Price(taxInclPrice, taxExclPrice, curr));
		this.setQuantity(quantity);		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
