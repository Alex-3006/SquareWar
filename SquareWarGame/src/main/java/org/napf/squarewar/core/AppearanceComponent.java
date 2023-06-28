package org.napf.squarewar.core;

public class AppearanceComponent {
	
	private double opacity;
	private double rotation;
	private double xOffset;
	private double yOffset;
	
	//Implemetierung Getter-Methode
	
	public double getOpacity() {
		return opacity;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	public double getXOffset() {
		return xOffset;
	}
	
	public double getYOffset() {
		return yOffset;
	}

	//Implementierung Setter-Methoden
	
	public void setOpacity(double opacity) {
		double ueberpruefen = opacity - 100;
		if(ueberpruefen <= 0 && opacity >= 0) {
			this.opacity = opacity;
		}
	}
	
	public void setRotation(double rotation) {
		if(rotation >= 0 && rotation <= 360) {
			this.rotation = rotation;
		}
	}
	
	public void setXOffset(double xOffset) {
		this.xOffset = xOffset;
	}
	
	public void setYOffset(double yOffset) {
		this.yOffset = yOffset;
	}
	
}
