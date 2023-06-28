package org.napf.squarewar.core;

import java.util.ArrayList;
import java.util.Arrays;

import org.napf.squarewar.exceptions.ActionMapperException;
import org.napf.squarewar.exceptions.MotorException;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class GameManager extends AnimationTimer {
	
	private ArrayList<KeyCode> queuedInputsPressed = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> queuedInputsReleased = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> lastFrameInputsPressed = new ArrayList<KeyCode>();;
	private boolean isMotorRunning;
	private float lastFrameTimeNanos;
	
	private static GameManager instance;
	private GameController gc;
	
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
		if (gc == null) {
			gc = GameController.getInstance();
		}
		lastFrameTimeNanos = System.currentTimeMillis() * 1000;
		isMotorRunning = true;
	}
	
	public void wuergTheMotorAb() {
		isMotorRunning = false;
	}
	
	private void uberCycle(float deltaTime) {		
		// Convert Inputs to InputActions
		ArrayList<InputAction> inputActions = new ArrayList<InputAction>();
		for (KeyCode k : queuedInputsPressed) {
			try {
				inputActions.addAll(Arrays.asList(ActionMapper.getActions(k, InputActionState.Press)));
				if (!lastFrameInputsPressed.contains(k)) {
					inputActions.addAll(Arrays.asList(ActionMapper.getActions(k, InputActionState.Down)));
				}
			} catch (ActionMapperException e) {
				continue;
			}
		}
		for (KeyCode k : queuedInputsReleased) {
			try {
				inputActions.addAll(Arrays.asList(ActionMapper.getActions(k, InputActionState.Up)));
			} catch (ActionMapperException e) {
				continue;
			}
		}
		
		// Send InputActions to GameController
		gc.setActions((InputAction[])inputActions.toArray());
		
		// Execute GameController Cycle
		GameController.getInstance().cycle();
		
		//Prepare for next frame
		lastFrameInputsPressed.removeAll(queuedInputsReleased);
		lastFrameInputsPressed.addAll(queuedInputsPressed);
	}
	
	public void queueInputs(boolean isReleased, KeyCode... keys) throws MotorException {
		if (!isMotorRunning) {
			throw new MotorException("Can't queue inputs while motor isn't running!");
		}
		
		if (isReleased) {
			queuedInputsReleased.addAll(Arrays.asList(keys));
		} else {
			queuedInputsPressed.addAll(Arrays.asList(keys));
		}
		
	}
	
}
