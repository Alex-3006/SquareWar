package org.napf.squarewar.core;
import java.math.*;
public class Projectile extends PhysicsObject{
  protected float speed;
  private double x;
  private double y;
  private double xpos;
  private double ypos;
  private double adjacent;
  private double opposite;  
  private boolean quickmaths;
  
  public Projectile(double xpos, double ypos) {
	  super(xpos, ypos);
	  this.xpos = xpos;
	  this.ypos = ypos;
	  adjacent = 0;
	  opposite = 0;
	  quickmaths = true;
  }
 
  public void update(int mouseXCord, int mouseYCord, int tankXCord, int tankYCord){
	  /*double h;
      double deg; 
      while( quickmaths == true) {
	  h = Math.sqrt(Math.pow((tankXCord - mouseXCord),2) + Math.pow((tankYCord - mouseYCord),2));
	  deg = Math.acos((tankXCord - mouseXCord) / h);
	  adjacent = Math.cos(deg) * speed;
	  opposite = Math.cos(deg) * speed;
	  quickmaths = false;
      }
	  x = xpos + adjacent;
	  y = ypos + opposite;
	  moveTo(x, y);
	  xpos = x;
	  ypos = y;*/
	    
	  double dirX, dirY;
	  
	  dirX = mouseXCord - tankXCord;
	  dirY = mouseYCord - tankYCord;
	  
	  
  }
   
}
