package fr.utbm.gl52.document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Client {

	@Id
	@GeneratedValue
	private int id;
	
	public Address address;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	
	public Client(Address address, String firstName, String lastName){
		this.setAddress(address);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firtstName) {
		this.firstName = firtstName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
