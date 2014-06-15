package fr.utbm.gl52.facturemodel;

public class Location {
	private ImageArea area;
	private String after;
	
	public Location() {
		this.area = new ImageArea(null, 0.,0.,1.,1.);
		this.after = null;
	}
	public Location(ImageArea area) {
		if(area != null)
			this.area = area;
		else
			this.area = new ImageArea(null, 0.,0.,1.,1.);
		this.after = null;
	}
	public Location(String after) {
		this.area = new ImageArea(null, 0.,0.,1.,1.);
		this.after = after;
	}
	public Location(ImageArea area, String after) {
		if(area != null)
			this.area = area;
		else
			this.area = new ImageArea(null, 0.,0.,1.,1.);
		this.after = after;
	}
	
	public ImageArea getArea() {
		return area;
	}
	public void setArea(ImageArea area) {
		if(area != null)
			this.area = area;
		else
			this.area = new ImageArea(null, 0.,0.,1.,1.);
	}

	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	
}
