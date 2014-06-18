package fr.utbm.gl52.document;

import java.awt.Image;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Supplier {

	@Id
	@GeneratedValue
	private int id;
	
	private String name = "";
	private String surname = "";
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address address = new Address();
	private String website ="";
	private String phoneNumber ="";
    @Lob
	private Image logo;
	

	/**
	 * @param name
	 * @param surname
	 * @param address
	 */
	public Supplier(String name,String surname, Address address){
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
