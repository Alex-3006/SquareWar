package org.napf.squarewar.core;

import java.awt.Color;

public class Rectangle extends AppearanceComponent {
	
	private double heigth;
	private double width;
	private Color color;
	
	//Implementierung Getter-Methoden
	
	public double getHeigth() {
		return heigth;
	}
	
	public double getWidth() {
		return width;
	}
	
	public Color getColor() {
		return color;
	}
	
	//Implementierung Setter-Methoden
	
	public void setHeight(double heigth) {
		this.heigth = heigth;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	

}
