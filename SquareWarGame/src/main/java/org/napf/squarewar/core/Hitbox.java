package org.napf.squarewar.core;

public class Hitbox {
   private double width;
   private double height;
   private double xOffset;
   private double yOffset;
   
   public Hitbox(double width ,double height, double xOffset, double yOffset) {
	   this.width = width;
	   this.height = height;
	   this.xOffset = xOffset;
	   this.yOffset = yOffset;
   }
}
