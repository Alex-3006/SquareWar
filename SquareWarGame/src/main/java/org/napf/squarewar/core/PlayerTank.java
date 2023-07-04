package org.napf.squarewar.core;

import org.napf.util.NapfMath;

import javafx.scene.paint.Color;

public class PlayerTank extends Tank implements InputListener {

	private double xpos;
	private double ypos;
	
	public PlayerTank(double xpos, double ypos) {
		  super(xpos, ypos, "PlayerTank", Color.BLUE);
		  speed = 4;
		  this.xpos = xpos;
		  this.ypos = ypos;
	  }

	@Override
	// Tank movement
	public void handleInputActions(InputAction[] inputActions) {
		for (InputAction ia : inputActions) {
			double inputX = 0, inputY = 0;
			
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
			
			if (ia.getActionName().equals("LeftMouseDown")) {
				shoot(GameManager.getInstance().convertCanvasToWorldX(GameManager.getInstance().getMousePosX()),GameManager.getInstance().convertCanvasToWorldY(GameManager.getInstance().getMousePosY()));
			}
			
			// Convert into unit vector
			if (!(inputX == 0 && inputY == 0)) {
				double magnitude = NapfMath.magnitude2D(inputX, inputY);
				inputX /= magnitude;
				inputY /= magnitude;
				moveBy(inputX * speed * GameManager.getInstance().getDeltaTime(), inputY * speed * GameManager.getInstance().getDeltaTime());
			}
		}
	}
	
	@Override
	public void update() {
		//System.out.println("What's häppnin? " + xpos + " " + ypos);
	}
	
	@Override
	public void shoot(double targetXPos, double targetYPos) {
		TankBullet tankbullet;
		tankbullet = new TankBullet(xpos,ypos);
		tankbullet.update(targetXPos,targetYPos,xpos,ypos);
	}
}
