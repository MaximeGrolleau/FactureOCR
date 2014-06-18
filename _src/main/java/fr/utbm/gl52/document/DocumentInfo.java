package fr.utbm.gl52.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class DocumentInfo {

	@Id
	@GeneratedValue
	private int id;


	private String seller;
	private float taxInclTotal;
	private float taxExclTotal;
	private int factureNumber;

	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Client client = new Client();
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Supplier supplier = new Supplier();
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Address store  = new Address();
	private Date date = new Date();
	@OneToMany(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private List<Product> products = new ArrayList<Product>();;
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Price total = new Price();
	

	public DocumentInfo() {}
	
	public DocumentInfo(Client client, Supplier supplier, Address store, Date date){
		this.setClient(client);
		this.setDate(date);
		this.setStore(store);
		this.setSupplier(supplier);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Address getStore() {
		return store;
	}

	public void setStore(Address store) {
		this.store = store;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Price getTotal() {
		return total;
	}

	public void setTotal(Price total) {
		this.total = total;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product){
		products.add(product);
	}
	public void removeProduct(Product product){
		products.remove(product);
	}
	
	public void updateProduct(Product oldProduct, Product newProduct){
		products.remove(oldProduct);
		products.add(newProduct);
	}
	
	public void setFactureNumber(String string) {
		this.factureNumber = DocumentBuilder.getIntFromString(string);
	}

	public int getFactureNumber() {
		return factureNumber;
	}

	public void setFactureNumber(int factureNumber) {
		this.factureNumber = factureNumber;
	}
}
