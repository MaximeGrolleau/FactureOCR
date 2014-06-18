package fr.utbm.gl52.document;

import java.awt.Image;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Supplier {

	@Id
	@GeneratedValue
	private int id;
	
	private String name = "";
	private Address address = new Address();
	private String website = "";
	private String phoneNumber = "";
	private Image logo;
	
	public Supplier(String name, Address address){
		this.setAddress(address);
		this.setName(name);
		setLogo(null);
	}
	
	public Supplier(String name, Address address, Image logo){
		this.setAddress(address);
		this.setName(name);
		this.setLogo(logo);
	}

	public Supplier() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
