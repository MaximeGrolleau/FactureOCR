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
public class Supplier{

	@Id
	@GeneratedValue
	private int supplierId;

	private String name = ""; //$NON-NLS-1$
	private String surname = ""; //$NON-NLS-1$
	private String phoneNumber = ""; //$NON-NLS-1$

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address address = new Address();
	private String website = ""; //$NON-NLS-1$

	@Lob
	private Image logo;
	

	/**
	 * @param name
	 * @param surname
	 * @param address
	 */
	public Supplier(String name, String surname, Address address) {
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

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
}
