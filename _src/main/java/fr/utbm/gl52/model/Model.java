package fr.utbm.gl52.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.utbm.gl52.document.DocumentType;

public class Model {
	
	private String name;
	private final DocumentType type;
	private Set<Tag> tags;
	private Tag productsArea;
	private List<Tag> productTags;
	
	public Model(DocumentType type) {
		this.type = type;
		this.tags = new HashSet<Tag>();
		this.productTags = new ArrayList<>();
	}

	public DocumentType getType() {
		return type;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public Tag getProductsArea() {
		return productsArea;
	}
	public void setProductsArea(Tag productsArea) {
		this.productsArea = productsArea;
	}

	public List<Tag> getProductTags() {
		return productTags;
	}
	public void addProductTag(Tag productTag) {
		this.productTags.add(productTag);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}