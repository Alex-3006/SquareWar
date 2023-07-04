package org.napf.squarewar.core;

import org.napf.util.NapfMath;

import javafx.scene.paint.Color;

public class PlayerTank extends Tank implements InputListener {

	
	public PlayerTank(double xpos, double ypos) {
		  super(xpos, ypos, "PlayerTank", Color.BLUE);
		  speed = 4;
	  }

	@Override
	public void handleInputActions(InputAction[] inputActions) {
		double inputX = 0, inputY = 0;
		for (InputAction ia : inputActions) {
			
			
			if (ia.getActionName().equals("MoveLeft")) {
				inputX = -1;
			}
			if (ia.getActionName().equals("MoveRight")) {
				inputX = 1;
			}
			if (ia.getActionName().equals("MoveUp")) {
				inputY = -1;
			}
			if (ia.getActionName().equals("MoveDown")) {
				inputY = 1;
			}
			
			if (ia.getActionName().equals("SpaceDown")) {
				new GameObject(2, 2, "Test spawn", new Rectangle(1, 3, Color.PINK));
			}
			
			// Convert into unit vector
			if (!(inputX == 0 && inputY == 0)) {
				double magnitude = NapfMath.magnitude2D(inputX, inputY);
				inputX /= magnitude;
				inputY /= magnitude;
			}
			
		}
		velX = inputX * speed;
		velY = inputY * speed;
	}
	
	@Override
	public void update() {
		//System.out.println("What's häppnin? " + xpos + " " + ypos);
	}
}
