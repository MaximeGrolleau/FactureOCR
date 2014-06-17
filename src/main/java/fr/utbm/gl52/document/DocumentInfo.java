package fr.utbm.gl52.document;

import java.util.Date;
import java.util.List;

public class DocumentInfo {

	private Client client;
	private Supplier supplier;
	private Address store;
	private Date date;
	private List<Product> products;
	private float taxInclTotal;
	private float taxExclTotal;
}
