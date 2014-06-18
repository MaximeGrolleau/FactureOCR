package fr.utbm.gl52.model;

import java.util.HashSet;
import java.util.Set;

import fr.utbm.gl52.document.DocumentType;

public class Model {
	
	private final DocumentType type;
	private Set<Tag> tags;
	
	public Model(DocumentType type) {
		this.type = type;
		this.tags = new HashSet<Tag>();
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
}