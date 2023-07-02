package org.napf.squarewar.core;

import javafx.scene.paint.Color;

public class Rectangle extends AppearanceComponent {
	
	private double width;
	private double height;
	private Color color;
	
	public Rectangle(double width, double height, Color color) {
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public Rectangle(double width, double height) {
		this(width, height, Color.GRAY);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(width, height, xOffset, yOffset);
	}
	
}