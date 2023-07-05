package org.napf.squarewar.core;

import javafx.scene.paint.Color;

public class Tile extends PhysicsObject {

	public Tile(double xpos, double ypos, String name) {
		super(xpos, ypos, name, new BoundingBox(1, 1, 0, 0), new Rectangle(1, 1, Color.GRAY));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollision(PhysicsObject collidingObject) {

	}
}