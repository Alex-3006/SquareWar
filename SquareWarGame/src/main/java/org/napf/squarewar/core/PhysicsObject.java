package org.napf.squarewar.core;

public class PhysicsObject extends GameObject{
	
	protected BoundingBox hitbox;
	protected double velX;
	protected double velY;
	protected boolean isCollideable;
  
  public PhysicsObject(double xpos, double ypos, String name, BoundingBox hitbox, AppearanceComponent... appearanceComponents) {
	  super(xpos, ypos, name, appearanceComponents);
	  this.hitbox = hitbox;
  }
  
  public PhysicsObject(double xpos, double ypos, String name, BoundingBox hitbox){
	  this(xpos, ypos, name, hitbox, new AppearanceComponent[0]);
  }
  
  public PhysicsObject(double xpos, double ypos, BoundingBox hitbox){
	  this(xpos, ypos, "new PhysicsObject", hitbox);
  }
  
  public void onCollision(PhysicsObject collidingObject) {
	  
  }

	public BoundingBox getHitbox() {
		return hitbox;
	}
	
	public void setHitbox(BoundingBox hitbox) {
		this.hitbox = hitbox;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public boolean isCollideable() {
		return isCollideable;
	}
	
	public void setCollideable(boolean isCollideable) {
		this.isCollideable = isCollideable;
	}
}
