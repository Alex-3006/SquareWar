package org.napf.squarewar.core;

public abstract class AppearanceComponent {
	protected double xOffset;
	protected double yOffset;
	
	public double getxOffset() {
		return xOffset;
	}



	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}



	public double getyOffset() {
		return yOffset;
	}



	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	/**
	 * Gets the bounding box of the AppearanceComponent in GameObject-relative space.
	 * @return the bounding box of the AppearanceComponent
	 */
	public abstract BoundingBox getBoundingBox();
}