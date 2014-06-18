package fr.utbm.gl52.model;

public class Tag {

	private String targetField;
	private Location location;
	
	public Tag(String targetField, Location location){
		this.setTargetField(targetField);
		this.setLocation(location);
	}

	public String getTargetField() {
		return targetField;
	}
	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
