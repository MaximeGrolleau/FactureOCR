package fr.utbm.gl52.model;

public class Tag {

	private ContentType content;
	private Location location;
	
	public Tag(ContentType content, Location location){
		this.setContent(content);
		this.setLocation(location);
	}

	public ContentType getContent() {
		return content;
	}

	public void setContent(ContentType content) {
		this.content = content;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
