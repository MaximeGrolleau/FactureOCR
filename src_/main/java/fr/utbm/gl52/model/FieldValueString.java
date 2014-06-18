package fr.utbm.gl52.model;

public class FieldValueString extends FieldValue<String> {

	public void extractData() {
		this.extractedValue = new String(this.textValue);
	}
}