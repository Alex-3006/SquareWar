package org.napf.squarewar.core;

import javafx.scene.paint.Color;



public class Tank extends PhysicsObject {
  protected float speed;
  private double xpos;
  private double ypos;
  
  public Tank(double xpos, double ypos, String name, Color color) {
	  super(xpos, ypos, name);
	  appearance.addComponent(new Rectangle(1, 1, color));
	  this.xpos = xpos;
	  this.ypos = ypos;
  }
  
  public float getSpeed() {
	return speed;
	}
	
	
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void shoot(double targetXPos, double targetYPos){
	  Projectile projectile;
	  projectile = new Projectile(xpos,ypos);
	  projectile.update(targetXPos,targetYPos,xpos,ypos);
  }
  
  public void alignGun(double x, double y){
	  
  }

  
}
