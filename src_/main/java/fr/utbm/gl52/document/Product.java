package fr.utbm.gl52.document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Product {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String reference;
	private float taxInclPrice;
	private float taxExclPrice;
	private int quantity;
	
	public Product(String name, String reference, float taxInclPrice, float taxExclPrice, int quantity){
		this.setName(name);
		this.setReference(reference);
		this.setTaxExclPrice(taxExclPrice);
		this.setTaxInclPrice(taxInclPrice);
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

	public float getTaxInclPrice() {
		return taxInclPrice;
	}

	public void setTaxInclPrice(float taxInclPrice) {
		this.taxInclPrice = taxInclPrice;
	}

	public float getTaxExclPrice() {
		return taxExclPrice;
	}

	public void setTaxExclPrice(float taxExclPrice) {
		this.taxExclPrice = taxExclPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
