package org.napf.squarewar.core;

public class GameController {

private static GameController instance;
	
	public static GameController getInstance() {
		return instance;
	}
	
	static {
		instance = new GameController();
	}
	
}
