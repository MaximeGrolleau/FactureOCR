<<<<<<< HEAD
package fr.utbm.gl52.facturemodel;

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
=======
package fr.utbm.gl52.facturemodel;

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
>>>>>>> 573c6ed774dfa4de591cce74a620bde42b186091
