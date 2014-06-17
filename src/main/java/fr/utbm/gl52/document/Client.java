package fr.utbm.gl52.document;

public class Client {

	private Address address;
	private String firstName;
	private String lastName;
	
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
