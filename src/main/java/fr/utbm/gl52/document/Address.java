package fr.utbm.gl52.document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
		
	@Id
	@GeneratedValue
	private int id;
	
	public String country = "";
	public String city = "";
	public String street = "";
	public int postalCode;
	
	
	public Address(String country, String city, String street){
		this.setCity(city);
		this.setCountry(country);
		this.setStreet(street);
	}
	
	public Address(String country, String city, String street, int postalCode){
		this.setCity(city);
		this.setCountry(country);
		this.setStreet(street);
		this.setPostalCode(postalCode);
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public void setPostalCode(String string) {
		this.postalCode = DocumentBuilder.getIntFromString(string);
	}
}
