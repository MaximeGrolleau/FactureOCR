package fr.utbm.gl52.facturemodel;

public class FieldValueString extends FieldValue<String> {

	public void extractData() {
		this.extractedValue = new String(this.textValue);
	}
}
