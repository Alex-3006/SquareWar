package org.napf.squarewar.core;

public class BoundingBox {
   private double width;
   private double height;
   private double xOffset;
   private double yOffset;
   
   public BoundingBox(double width, double height, double xOffset, double yOffset) {
	   this.width = width;
	   this.height = height;
	   this.xOffset = xOffset;
	   this.yOffset = yOffset;
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
   
   
   
}
