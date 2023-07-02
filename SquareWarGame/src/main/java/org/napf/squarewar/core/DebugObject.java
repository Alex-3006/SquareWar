package org.napf.squarewar.core;

import javafx.scene.paint.Color;

public class DebugObject extends GameObject implements InputListener {

	private GameManager gm;
	private GameController gc;
	
	public DebugObject(double xpos, double ypos, String name) {
		super(xpos, ypos, name);
		gm = GameManager.getInstance();
		gc = GameController.getInstance();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void handleInputActions(InputAction[] inputActions) {
		for (InputAction ia : inputActions) {
			if (ia.getActionName().equals("LeftMouseDown")) {
				new GameObject(gm.convertCanvasToWorldX(gm.getMousePosX()), gm.convertCanvasToWorldY(gm.getMousePosY()), "Random Blob", new Rectangle(0.5, 0.5, Color.PINK));
			}
			
			if (ia.getActionName().equals("RightMouseDown")) {
				GameObject randomBlob = gc.findGameObject("Random Blob");
				if (randomBlob != null) {
					gc.killGameObject(randomBlob);
				}
			}
		}
	}
	
	
	
}