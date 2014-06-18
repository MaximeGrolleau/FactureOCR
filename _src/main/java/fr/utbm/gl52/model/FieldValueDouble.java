package fr.utbm.gl52.model;

import java.text.NumberFormat;
import java.text.ParseException;

public class FieldValueDouble extends FieldValue<Double> {

	public void extractData() {
		NumberFormat numberFormater = NumberFormat.getInstance();

		try {
			this.extractedValue = numberFormater.parse(this.textValue).doubleValue();
		} catch (ParseException e) {
			this.extractedValue = 0.0;
		}
	}
}