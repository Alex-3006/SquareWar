package org.napf.squarewar.core;

public class Tank extends PhysicsObject{
  protected float speed;
  private double x;
  private double y;
  private double xpos;
  private double ypos;
  
  public Tank(double xpos, double ypos) {
	  super(xpos, ypos);
	  this.xpos = xpos;
	  this.ypos = ypos;
  }
  
  public void shoot(){
	  
  }
  
  public void alignGun(double x, double y){
	  
  }
  
  public void update(){
	  
	  moveTo(x, y);
	  xpos = x;
	  ypos = y;
	  
	  
  }
  
}
