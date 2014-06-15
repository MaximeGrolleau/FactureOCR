<<<<<<< HEAD
package fr.utbm.gl52.facturemodel;

import java.util.ArrayList;
import java.util.List;

public class Model {
	public static enum DOCUMENT_TYPE  { BILL, RECEIPT }
	private final DOCUMENT_TYPE type;
	private final String name;
	private List<ModelField> fields;
	
	public Model(DOCUMENT_TYPE type, String name) {
		this.name = name;
		this.type = type;
		this.fields = new ArrayList<ModelField>();
	}

	public DOCUMENT_TYPE getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public List<ModelField> getFields() {
		return fields;
	}

	public void addField(ModelField field) {
		this.fields.add(field);
	}
}
=======
package fr.utbm.gl52.facturemodel;

import java.util.ArrayList;
import java.util.List;

public class Model {
	public static enum DOCUMENT_TYPE  { BILL, RECEIPT }
	private final DOCUMENT_TYPE type;
	private final String name;
	private List<ModelField> fields;
	
	public Model(DOCUMENT_TYPE type, String name) {
		this.name = name;
		this.type = type;
		this.fields = new ArrayList<ModelField>();
	}

	public DOCUMENT_TYPE getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public List<ModelField> getFields() {
		return fields;
	}

	public void addField(ModelField field) {
		this.fields.add(field);
	}
}
>>>>>>> 573c6ed774dfa4de591cce74a620bde42b186091
