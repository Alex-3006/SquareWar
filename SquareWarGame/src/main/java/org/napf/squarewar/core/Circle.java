package org.napf.squarewar.core;

import java.awt.Color;

public class Circle extends AppearanceComponent {

	private double radius;
	private double borderThickness;
	private Color color;
	
	//Implementierung Getter-Methoden
	
	public double getRadius() {
		return radius;
	}
	
	public double getBorderThickness() {
		return borderThickness;
	}
	
	public Color getColor() {
		return color;
	}
	
	//Implementierung Setter-Methoden
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public void setBorderThickness(double borderThickness) {
		this.borderThickness = borderThickness;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	
}
