package fr.utbm.gl52.model;

public class ImageArea {
	private ImageArea relative;
	private double fromX;
	private double fromY;
	private double toX;
	private double toY;
	private boolean scale;

	ImageArea() {
		this.relative = null;
		this.fromX = 0.0;
		this.fromY = 0.0;
		this.toX = 1.0;
		this.toY = 1.0;
		this.scale = true;
	}
	public ImageArea(ImageArea relative, double fromX, double fromY, double toX, double toY, boolean scale) {
		this.relative = relative;
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.scale = scale;
	}
	ImageArea(double fromX, double fromY, double width, double height, boolean scale, ImageArea relative) {
		this.relative = relative;
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = Math.abs(fromX + width);
		this.toY = Math.abs(fromY + width);
		this.scale = scale;
	}
	
	public ImageArea getRelative() {
		return relative;
	}
	public void setRelative(ImageArea relative) {
		this.relative = relative;
	}

	public double getFromX() {
		if(this.relative != null)
			return this.fromX + this.relative.getFromX();
		return this.fromX;
	}
	public double getFromY() {
		if(this.relative != null)
			return this.fromY + this.relative.getFromY();
		return this.fromY;
	}
	public double getToX() {
		if(this.relative != null)
			return this.toX + this.relative.getToX();
		return this.toX;
	}
	public double getToY() {
		if(this.relative != null)
			return this.toY + this.relative.getToY();
		return this.toY;
	}
	public double getWidth() {
		return Math.abs(getFromX() - getToX());
	}
	public double getHeight() {
		return Math.abs(getFromY() - getToY());
	}
	public boolean getScale() {
		return scale;
	}
	public void setScale(boolean scale) {
		this.scale = scale;
	}
	
}
