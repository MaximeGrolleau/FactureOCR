<<<<<<< HEAD
package fr.utbm.gl52.facturemodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Field {
	private String name;
	private List<FieldValue<?>> values;

	public Field(FieldValue<?>...fieldValues) {
		values = new ArrayList<FieldValue<?>>(Arrays.asList(fieldValues));
	}
	public Field(String name, FieldValue<?>...fieldValues) {
		if(name != null)
			name = new String(name);
		values = new ArrayList<FieldValue<?>>(Arrays.asList(fieldValues));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FieldValue<?>> getValues() {
		return values;
	}
	public void setValue(List<FieldValue<?>> values) {
		this.values = values;
	}
}
=======
package fr.utbm.gl52.facturemodel;

public class Field {

}
>>>>>>> 573c6ed774dfa4de591cce74a620bde42b186091
