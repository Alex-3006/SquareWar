package org.napf.squarewar.core;

public class GameController {

	private static GameController instance;
	
	private InputAction[] actions;
	
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
		
		//Execute GameObject-Update
		
		//Execute Physics-Update
	}
	
}
