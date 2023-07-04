package org.napf.squarewar.core;

import org.napf.util.NapfMath;

import javafx.scene.paint.Color;

public class Camera extends GameObject implements InputListener {

	private double height;

	public Camera(double xpos, double ypos, boolean isMainCamera) {
		super(xpos, ypos, isMainCamera ? "MainCamera" : "Camera");
		// TODO Auto-generated constructor stub
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void handleInputActions(InputAction[] inputActions) {
		if (!GameController.getInstance().isDebugMode())
			return;

		for (InputAction ia : inputActions) {
			double inputX = 0, inputY = 0;

			if (ia.getActionName().equals("CamMoveLeft")) {
				inputX = -1;
			}
			if (ia.getActionName().equals("CamMoveRight")) {
				inputX = 1;
			}
			if (ia.getActionName().equals("CamMoveUp")) {
				inputY = -1;
			}
			if (ia.getActionName().equals("CamMoveDown")) {
				inputY = 1;
			}

			// Convert into unit vector
			if (!(inputX == 0 && inputY == 0)) {
				double magnitude = NapfMath.magnitude2D(inputX, inputY);
				inputX /= magnitude;
				inputY /= magnitude;
				moveBy(inputX * 4 * GameManager.getInstance().getDeltaTime(), inputY * 4 * GameManager.getInstance().getDeltaTime());
			}
		}
	}
}