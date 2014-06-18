package fr.utbm.gl52.document;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DocumentInfo {

	@Id
	@GeneratedValue
	private int id;
	
	private Client client;
	private Supplier supplier;
	private Address store;
	private Date date;
	private List<Product> products;
	private float taxInclTotal;
	private float taxExclTotal;
	private int factureNumber;

	public DocumentInfo() {
	}
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

	public float getTaxInclTotal() {
		return taxInclTotal;
	}

	public void setTaxInclTotal(float taxInclTotal) {
		this.taxInclTotal = taxInclTotal;
	}

	public float getTaxExclTotal() {
		return taxExclTotal;
	}

	public void setTaxExclTotal(float taxExclTotal) {
		this.taxExclTotal = taxExclTotal;
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
	public int getFactureNumber() {
		return factureNumber;
	}
	public void setFactureNumber(int factureNumber) {
		this.factureNumber = factureNumber;
	}
	public void setFactureNumber(String string) {
		this.factureNumber = DocumentBuilder.getIntFromString(string);
	}
}
