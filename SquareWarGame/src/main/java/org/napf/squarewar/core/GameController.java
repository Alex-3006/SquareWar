package org.napf.squarewar.core;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class GameController {

	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
	private ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();
	private InputAction[] inputActions;
	private boolean isDebugMode;
	
	private static GameController instance;
	private Camera mainCamera;
	
	private GameController() {
		
	}
	
	static {
		instance = new GameController();
		instance.init();
	}
	
	public static GameController getInstance() {
		return instance;
	}
	
	private void init() {
		mainCamera = new Camera(0, 0, true);
		mainCamera.setHeight(8);
		new PlayerTank(3, 1);
		new GameObject(-1, -2, "Gray Blob", new Rectangle(3, 1.5, Color.GRAY));
		new DebugObject(0, 1, "DebugObject");
	}
	
	public void setInputActions(InputAction[] inputActions) {
		this.inputActions = inputActions;
	}
	
	public void cycle() {
		for (InputAction ia : inputActions) {
			//if (ia.getActionState() != InputActionState.Press)
				//System.out.println("Pressed Button " + ia.getActionKey() + " causing action " + ia.getActionName() + " with state " + ia.getActionState());
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
	
	/**
	 * Finds a GameObject with the given name.
	 * @param name The name of the GameObject to search for
	 * @return The first found GameObject with the given name. Null if no fitting GameObject is found.
	 */
	public GameObject findGameObject(String name) {
		for (GameObject g : gameObjects) {
			if (g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}
	
	/**
	 * Finds GameObjects with the given name.
	 * @param name The name of the GameObjects to search for
	 * @return Every found GameObject with the given name. Null if no fitting GameObject is found.
	 */
	public GameObject[] findGameObjects(String name) {
		ArrayList<GameObject> foundObjects = new ArrayList<GameObject>();
		for (GameObject g : gameObjects) {
			if (g.getName().equals(name)) {
				foundObjects.add(g);
			}
		}
		return foundObjects.size() > 0 ? foundObjects.toArray(new GameObject[foundObjects.size()]) : null;
	}
	
	/**
	 * Adds the provided gameObject to the GameController register. This step is automatically done when initializing a GameObject and shouldn't be called on its own.
	 * @param gameObject
	 */
	public void registerGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
		if (gameObject instanceof PhysicsObject) {
			physicsObjects.add((PhysicsObject)gameObject);
		}
		if (gameObject instanceof InputListener) {
			inputListeners.add((InputListener)gameObject);
		}
	}
	
	public void killGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);
		if (gameObject instanceof PhysicsObject) {
			physicsObjects.remove((PhysicsObject)gameObject);
		}
		if (gameObject instanceof InputListener) {
			inputListeners.remove((InputListener)gameObject);
		}
	}
	
	public Camera getMainCamera() {
		return mainCamera;
	}
	
	public ArrayList<GameObject> getAllGameObjects() {
		return new ArrayList<GameObject>(gameObjects);
	}
	
	public int getGameObjectCount() {
		return gameObjects.size();
	}
	
	public boolean isDebugMode() {
		return isDebugMode;
	}

	public void setDebugMode(boolean isDebugMode) {
		this.isDebugMode = isDebugMode;
	}
}