package org.napf.squarewar.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.napf.squarewar.exceptions.ActionMapperException;
import org.napf.squarewar.exceptions.MotorException;
import org.napf.squarewar.mvc.GameOverView;
import org.napf.squarewar.mvc.GameView;
import org.napf.squarewar.mvc.View;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameManager extends AnimationTimer {

	private ArrayList<KeyCode> queuedInputsPressed = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> queuedInputsReleased = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> lastFrameInputsPressed = new ArrayList<KeyCode>();;
	private boolean isMotorRunning;
	private float lastFrameTimeNanos;
	private float deltaTime;
	private double mousePosX;
	private double mousePosY;
	private DoubleProperty gameViewWidth;
	private DoubleProperty gameViewHeight;

	private static GameManager instance;
	private GameController gc;
	private GameView view;

	private float startMilliSeconds = 0;
	private float pauseStartMilliSeconds = 0;
	private float pauseEndMilliSeconds = 0;
	private float pauseTimeTotal = 0;

	private float respawnStart = 0;
	
	private boolean respawnEnd = false;
	
	private boolean gameOver = false;
	private boolean created = false;

	public static GameManager getInstance() {
		return instance;
	}

	public GameManager() {
		gameViewWidth = new SimpleDoubleProperty();
		gameViewHeight = new SimpleDoubleProperty();
	}

	static {
		instance = new GameManager();
	}

	public void gameOver() {
		Stage stage = new Stage();
		View gameOverMenu = null;
		try {
			gameOverMenu = new GameOverView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(gameOverMenu);
		stage.setScene(scene);
		stage.show();
		wuergTheMotorAb();
	}
	
	@Override
	public void handle(long now) {
		if (isMotorRunning) {
			float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
			deltaTime = secondsSinceLastFrame;
			lastFrameTimeNanos = now;
			uberCycle(secondsSinceLastFrame);
		}

		isTimeUp();
		if (isTimeUp() == true && created == false && gameOver == true) {
			created = true;
			gameOver();
		}
		
		respawnEnd();
		if (respawnEnd() == true) {
			respawnEnd = true;
		}
	}

	public boolean isTimeUp() {
		 if (((startMilliSeconds + 300000 + pauseTimeTotal) / 1e9) <= System.currentTimeMillis() / 1e9) {
			gameOver = true;
		}

		return gameOver;
	}
	
	public boolean respawnEnd() {
		 if (((respawnStart + 15000) / 1e9) <= System.currentTimeMillis() / 1e9) {
			respawnEnd = true;
		}
		 
		return respawnEnd;
	}
	
	public void resetRespawn() {
		respawnEnd = false;
		respawnStart = 0;
	}

	public void initializeRespawn() {
		GameManager.getInstance().respawnStart = System.currentTimeMillis();
		GameManager.getInstance().respawnEnd = false;
	}
	
	public void zuendTheMotorAn(GameView view) {
		this.view = view;
		if (gc == null) {
			gc = GameController.getInstance();
		}
		lastFrameTimeNanos = System.currentTimeMillis() * 1000;
		isMotorRunning = true;
		startMilliSeconds = System.currentTimeMillis();
		gameOver = false;
		created = false;
		start();
	}

	public void pauseTheMotor() {
		stop();
		pauseStartMilliSeconds = System.currentTimeMillis();
		isMotorRunning = false;
	}

	public void resumeTheMotor(GameView view) {
		this.view = view;
		if (gc == null) {
			gc = GameController.getInstance();
		}
		pauseEndMilliSeconds = System.currentTimeMillis();
		pauseTimeTotal += pauseEndMilliSeconds - pauseStartMilliSeconds;
		pauseEndMilliSeconds = 0;
		pauseStartMilliSeconds = 0;
		isMotorRunning = true;
		start();
	}

	public void wuergTheMotorAb() {
		stop();
		pauseTimeTotal = 0;
		isMotorRunning = false;
	}

	private void uberCycle(float deltaTime) {
		// Prepare for frame

		// Convert Inputs to InputActions
		ArrayList<InputAction> inputActions = new ArrayList<InputAction>();
		for (KeyCode k : queuedInputsPressed) {
			try {
				if (!lastFrameInputsPressed.contains(k)) {
					inputActions.addAll(Arrays.asList(ActionMapper.getActions(k, InputActionState.Down)));
					if (inputActions.get(inputActions.size() - 1).getActionName().equals("DebugToggle")) {
						view.setDebugMode(!view.isDebugMode());
						gc.setDebugMode(!gc.isDebugMode());
					}
				}
				inputActions.addAll(Arrays.asList(ActionMapper.getActions(k, InputActionState.Press)));
			} catch (ActionMapperException e) {
				continue;
			}
		}
		for (KeyCode k : lastFrameInputsPressed) {
			try {
				if (!queuedInputsPressed.contains(k)) {
					inputActions.addAll(Arrays.asList(ActionMapper.getActions(k, InputActionState.Press)));
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
		gc.setInputActions((InputAction[]) inputActions.toArray(new InputAction[inputActions.size()]));

		// Execute GameController Cycle
		GameController.getInstance().cycle();

		lastFrameInputsPressed.removeAll(queuedInputsReleased);
		lastFrameInputsPressed.addAll(queuedInputsPressed);
		queuedInputsPressed = new ArrayList<KeyCode>();
		queuedInputsReleased = new ArrayList<KeyCode>();

		// draw
		view.clear();
		view.prepare(gc.getMainCamera());
		view.render(gc.getAllGameObjects().toArray(new GameObject[gc.getGameObjectCount()]));
	}

	public void queueInputs(boolean isReleased, KeyCode... keys) throws MotorException {
		if (!isMotorRunning) {
			throw new MotorException("Can't queue inputs while motor isn't running!");
		}

		for (KeyCode k : keys) {
			if (isReleased) {
				if (!queuedInputsReleased.contains(k))
					queuedInputsReleased.add(k);
			} else {
				if (!queuedInputsPressed.contains(k) && !lastFrameInputsPressed.contains(k)) {
					queuedInputsPressed.add(k);
				}
			}
		}
	}

	public float getLastFrameTimestamp() {
		return (float) (lastFrameTimeNanos / 1E9);
	}

	public float getDeltaTime() {
		return deltaTime;
	}

	public double getMousePosX() {
		return mousePosX;
	}

	public void setMousePosX(double mousePosX) {
		this.mousePosX = mousePosX;
	}

	public double getMousePosY() {
		return mousePosY;
	}

	public void setMousePosY(double mousePosY) {
		this.mousePosY = mousePosY;
	}

	public DoubleProperty getGameViewWidth() {
		return gameViewWidth;
	}

	public void setGameViewWidth(DoubleProperty gameViewWidth) {
		this.gameViewWidth = gameViewWidth;
	}

	public DoubleProperty getGameViewHeight() {
		return gameViewHeight;
	}

	public void setGameViewHeight(DoubleProperty gameViewHeight) {
		this.gameViewHeight = gameViewHeight;
	}

	public double convertCanvasToWorldX(Camera cam, double canvasX) {
		return (cam.getXpos()
				+ (canvasX - gameViewWidth.doubleValue() / 2) / (gameViewHeight.doubleValue() / cam.getHeight()));
	}

	public double convertCanvasToWorldY(Camera cam, double canvasY) {
		return (cam.getYpos()
				+ (canvasY - gameViewHeight.doubleValue() / 2) / (gameViewHeight.doubleValue() / cam.getHeight()));
	}

	public double convertWorldToCanvasX(Camera cam, double canvasX) {
		return (gameViewWidth.doubleValue() / 2
				+ (canvasX - cam.getXpos()) * (gameViewHeight.doubleValue() / cam.getHeight()));
	}

	public double convertWorldToCanvasY(Camera cam, double canvasY) {
		return (gameViewHeight.doubleValue() / 2
				+ (canvasY - cam.getYpos()) * (gameViewHeight.doubleValue() / cam.getHeight()));
	}

	public double convertCanvasToWorldX(double canvasX) {
		return convertCanvasToWorldX(gc.getMainCamera(), canvasX);
	}

	public double convertCanvasToWorldY(double canvasY) {
		return convertCanvasToWorldY(gc.getMainCamera(), canvasY);
	}

	public double convertWorldToCanvasX(double canvasX) {
		return convertWorldToCanvasX(gc.getMainCamera(), canvasX);
	}

	public double convertWorldToCanvasY(double canvasY) {
		return convertWorldToCanvasY(gc.getMainCamera(), canvasY);
	}
}