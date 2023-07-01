package org.napf.squarewar.core;

public class Appearance {
	
	private AppearanceComponent[] appearanceComponents;
	private double opacity;
	private double rotation;
	
	public Appearance() {
		appearanceComponents = new AppearanceComponent[1000];
	}
	
	//Implementierung Getter-Methoden
	
	public AppearanceComponent getAppearanceComponent(int positionInArray) {
		return appearanceComponents[positionInArray];
	}
	
	public AppearanceComponent[] getAppearanceComponents() {
		return appearanceComponents;
	}
	
	public double getOpacity() {
		return opacity;
	}
	
	public double getRotation() {
		return rotation;
	}
	
	//Implementierung Setter-Methoden
	
	public void setAppearanceComponent(int positionInArray, AppearanceComponent appearanceComponent) {
		appearanceComponents[positionInArray] = appearanceComponent;
	}
	
	public void setOpacity(double opacity) {
		if(opacity >= 0 && opacity <= 100) {
			this.opacity = opacity;
		} else {
			System.out.println("Value Opacity = wrong, Value range: 0-100 %");
		}
	}
	
	public void setRotation(double rotation) {
		if(rotation >= 0 && rotation <= 360) {
			this.rotation = rotation;
		}
	}
}
