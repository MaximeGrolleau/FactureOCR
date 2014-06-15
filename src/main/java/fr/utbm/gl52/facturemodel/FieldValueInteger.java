<<<<<<< HEAD
package fr.utbm.gl52.facturemodel;

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
=======
package fr.utbm.gl52.facturemodel;

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
>>>>>>> 573c6ed774dfa4de591cce74a620bde42b186091
