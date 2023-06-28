package org.napf.squarewar.core;
import java.math.*;
public class Projectile extends PhysicsObject{
  private float speed;
  private double x;
  private double y;
  private double xpos;
  private double ypos;
  
  
  public Projectile(double xpos, double ypos) {
	  super(xpos, ypos);
	  this.xpos = xpos;
	  this.ypos = ypos;
  }
 
  public void update(int mouseXCord, int mouseYCord, int tankXCord, int tankYCord){
	  double h;
      double deg;
      double adjacent;
      double opposite;
	  h = Math.sqrt(Math.pow((tankXCord - mouseXCord),2) + Math.pow((tankYCord - mouseYCord),2));
	  deg = Math.acos((tankXCord - mouseXCord) / h);
	  adjacent = Math.cos(deg) * speed;
	  opposite = Math.cos(deg) * speed;
	  while(true) {
		  x = xpos + adjacent;
		  y = ypos + opposite;
		  moveTo(x, y);
		  x = xpos;
		  y = ypos;
	  }
	  
  }
  
  
}
