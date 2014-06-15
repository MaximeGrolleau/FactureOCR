<<<<<<< HEAD
package fr.utbm.gl52.facturemodel;


public class ModelField {
	private Location location;
	private Field field;
	
	public ModelField(Location location, FieldValue<?>...fieldValues) {
		this.location = location;
		this.field = new Field(location.getAfter(), fieldValues);
	}
	public ModelField(Location location, Field field) {
		this.location = location;
		this.field = field;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}

}
=======
package fr.utbm.gl52.facturemodel;


public class ModelField {
	private Location location;
	private Field field;
	
	public ModelField(Location location, FieldValue<?>...fieldValues) {
		this.location = location;
		this.field = new Field(location.getAfter(), fieldValues);
	}
	public ModelField(Location location, Field field) {
		this.location = location;
		this.field = field;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}

}
>>>>>>> 573c6ed774dfa4de591cce74a620bde42b186091
