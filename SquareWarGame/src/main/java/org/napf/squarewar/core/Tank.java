package org.napf.squarewar.core;

import javafx.scene.paint.Color;

public class Tank extends PhysicsObject {
  protected float speed;
  
  public Tank(double xpos, double ypos, String name, Color color) {
	  super(xpos, ypos, name);
	  appearance.addComponent(new Rectangle(1, 1, color));
  }
  
  public float getSpeed() {
	return speed;
	}
	
	
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void shoot(){
	  
  }
  
  public void alignGun(double x, double y){
	  
  }
  
}
