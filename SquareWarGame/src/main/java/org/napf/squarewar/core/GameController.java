package org.napf.squarewar.core;

import java.util.ArrayList;

public class GameController {

	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
	private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	private InputAction[] inputActions;
	
	private static GameController instance;
	
	static {
		instance = new GameController();
	}
	
	public static GameController getInstance() {
		return instance;
	}
	
	public void setInputActions(InputAction[] inputActions) {
		this.inputActions = inputActions;
	}
	
	public void cycle() {
		for (InputAction ia : inputActions) {
			System.out.println("Pressed Button " + ia.getActionKey() + " causing action " + ia.getActionName() + " with state " + ia.getActionState());
		}
		
		//Execute InputListener-Update
		for (InputListener il : inputListeners) {
			il.handleInputActions(inputActions);
		}
		
		//Execute GameObject-Update
		for (GameObject go : gameObjects) {
			go.update();
		}
		
		//Execute Physics-Update
		//TODO Physics.collisionCycle(physicsObjects);
	}
	
}
