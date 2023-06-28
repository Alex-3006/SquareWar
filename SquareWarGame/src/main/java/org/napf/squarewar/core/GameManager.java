package org.napf.squarewar.core;

import javafx.animation.AnimationTimer;

public class GameManager extends AnimationTimer {

	private static GameManager instance;
	
	public static GameManager getInstance() {
		return instance;
	}
	
	static {
		instance = new GameManager();
	}

	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
		
	}
	
	
}
