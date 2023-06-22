package org.napf.squarewar.core;

public class PhysicsObject extends GameObject{
  private Hitbox hitbox;
  private boolean isCollideable;
  
  public PhysicsObject(double xpos, double ypos) {
	  super(xpos,ypos);
  }
  
  public void OnCollision() {
	  
  }
}
