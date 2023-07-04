package org.napf.squarewar.core;

public class Circle extends AppearanceComponent {

	protected double radius;

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(radius * 2, radius * 2, xOffset, yOffset);
	}

}
