package org.napf.squarewar.core;

import java.util.ArrayList;

public class GameController {

	private ArrayList<GameObject> gameObjects;
	private ArrayList<PhysicsObject> physicsObjects;
	private ArrayList<InputListener> inputListeners;
	private InputAction[] actions;
	
	private static GameController instance;
	
	static {
		instance = new GameController();
	}
	
	public static GameController getInstance() {
		return instance;
	}
	
	public void setActions(InputAction[] actions) {
		this.actions = actions;
	}
	
	public void cycle() {
		//Execute InputListener-Update
		for (InputListener il : inputListeners) {
			il.handleInputActions(actions);
		}
		
		//Execute GameObject-Update
		for (GameObject go : gameObjects) {
			go.update();
		}
		
		//Execute Physics-Update
		//TODO Physics.collisionCycle(physicsObjects);
	}
	
}
