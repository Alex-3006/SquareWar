package org.napf.squarewar.core;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class GameManager extends AnimationTimer {

	private static GameManager instance;
	
	private ArrayList<KeyCode> queuedInputs;
	private boolean isMotorRunning;
	private float lastFrameTimeNanos;
	
	public static GameManager getInstance() {
		return instance;
	}
	
	static {
		instance = new GameManager();
	}

	@Override
	public void handle(long now) {
		
		if (isMotorRunning) {
			float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
	        lastFrameTimeNanos = now;
	        uberCycle(secondsSinceLastFrame);
		}
		
	}
	
	public void zuendTheMotorAn() {
		lastFrameTimeNanos = System.currentTimeMillis() * 1000;
		isMotorRunning = true;
	}
	
	public void wuergTheMotorAb() {
		isMotorRunning = false;
	}
	
	private void uberCycle(float deltaTime) {
		// Get Queued Inputs from Model
		
		// Convert Inputs to InputActions
		
		// Send InputActions to GameController
		// Execute GameController Cycle
		GameController.getInstance().cycle();
	}
	
	public void queueInput(KeyCode keys...) {
		
	}
	
}
