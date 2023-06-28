package org.napf.squarewar.core;

public class GameObject {
   private double xpos;
   private double ypos;
  //private String name;
   private Appearance appearance;
   
   public GameObject(double xpos, double ypos){

	   this.xpos = xpos;
	   this.ypos = ypos;
   }
   

   public void moveBy(double x, double y) {
	   
	   xpos = xpos + x;
	   ypos = ypos + y;
   }
   
   public void moveTo(double x, double y) {
	   
	   xpos = x;
	   ypos = y;
	   
   }
   
   public void update(){
	   
   }
}
