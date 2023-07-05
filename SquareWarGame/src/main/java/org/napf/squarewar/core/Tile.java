package org.napf.squarewar.core;

import javafx.scene.paint.Color;

public class Tile extends PhysicsObject {

	public Tile(double xpos, double ypos, double size, String name) {
		super(xpos, ypos, name, new BoundingBox(size, size, 0, 0), new Rectangle(size, size, Color.GRAY));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollision(PhysicsObject collidingObject) {
		
	}
}
