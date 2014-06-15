<<<<<<< HEAD
package fr.utbm.gl52.facturemodel;

public abstract class FieldValue<T> {
	protected String textValue;
	protected T extractedValue;
	
	public final void extract() {
		extractedValue = null;
		if(textValue != null)
			extractData();
	}
	protected void extractData() {
	}

	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public T getExtractedValue() {
		return extractedValue;
	}
	public void setExtractedValue(T extractedValue) {
		this.extractedValue = extractedValue;
	}
}
=======
package fr.utbm.gl52.facturemodel;

public abstract class FieldValue<T> {
	protected String textValue;
	protected T extractedValue;
	
	public final void extract() {
		extractedValue = null;
		if(textValue != null)
			extractData();
	}
	protected void extractData() {
	}

	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public T getExtractedValue() {
		return extractedValue;
	}
	public void setExtractedValue(T extractedValue) {
		this.extractedValue = extractedValue;
	}
}
>>>>>>> 573c6ed774dfa4de591cce74a620bde42b186091
