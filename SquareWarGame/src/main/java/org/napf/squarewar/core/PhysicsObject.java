package org.napf.squarewar.core;

public class PhysicsObject extends GameObject{
  private BoundingBox hitbox;
  private boolean isCollideable;
  
  public PhysicsObject(double xpos, double ypos, String name, AppearanceComponent... appearanceComponents) {
	  super(xpos, ypos, name, appearanceComponents);
  }
  
  public PhysicsObject(double xpos, double ypos, String name){
	  this(xpos, ypos, name, new AppearanceComponent[0]);
  }
  
  public PhysicsObject(double xpos, double ypos){
	  this(xpos, ypos, "new PhysicsObject");
  }
  
  public void OnCollision() {
	  
  }
}