package fr.utbm.gl52.model;

import java.text.NumberFormat;
import java.text.ParseException;

public class FieldValueInteger extends FieldValue<Integer> {

	public void extractData() {
		NumberFormat numberFormater = NumberFormat.getInstance();

		try {
			this.extractedValue = numberFormater.parse(this.textValue).intValue();
		} catch (ParseException e) {
			this.extractedValue = 0;
		}
	}
}
