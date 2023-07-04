package org.napf.squarewar.mvc;

import java.io.IOException;

import org.napf.squarewar.core.GameController;
import org.napf.squarewar.core.GameManager;
import org.napf.squarewar.mvc.GameMenuView;
import org.napf.squarewar.mvc.GameView;
import org.napf.squarewar.mvc.GameViewController;
import org.napf.squarewar.mvc.RespawnView;
import org.napf.squarewar.mvc.StartMenuView;
import org.napf.squarewar.mvc.View;
import org.napf.squarewar.exceptions.MotorException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameViewController {
	GameManager gm;
	GameView gameView;
	GameMenuView gameMenu;
	RespawnView respawn;
	StackPane stackPane;
	Scene gameScene;
	Scene menuScene;
	Scene respawnScene;

	public GameViewController() throws IOException {
		gm = new GameManager();
		gameView = new GameView();
		gameMenu = new GameMenuView();
		respawn = new RespawnView();
		stackPane = new StackPane(gameView);
		gameScene = new Scene(stackPane, 720, 640);
		menuScene = new Scene(gameMenu, 720, 640);
		respawnScene = new Scene(respawn, 720, 640);
	}

	public void keyInputHandling(Scene scene, Stage stage) {
		// Key Input handling
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			try {
				GameManager.getInstance().queueInputs(false, key.getCode());
			} catch (MotorException e) {
				e.printStackTrace();
			}

			if (key.getCode() == KeyCode.ESCAPE) {
				try {
					showGameMenu(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (key.getCode() == KeyCode.R) {
				try {
					showRespawn(stage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			try {
				GameManager.getInstance().queueInputs(true, key.getCode());
			} catch (MotorException e) {
				e.printStackTrace();
			}
		});
		gameView.setOnMousePressed(event -> {
			System.out.println("( " + event.getX() + " | " + event.getY() + " )");
			try {
				if (event.getButton() == MouseButton.PRIMARY) {
					GameManager.getInstance().queueInputs(false, KeyCode.F20);
				} else if (event.getButton() == MouseButton.SECONDARY) {
					GameManager.getInstance().queueInputs(false, KeyCode.F21);
				} else if (event.getButton() == MouseButton.MIDDLE) {
					GameManager.getInstance().queueInputs(false, KeyCode.F22);
				}
			} catch (MotorException e) {
				e.printStackTrace();
			}
		});
		gameView.setOnMouseReleased(event -> {
			try {
				if (event.getButton() == MouseButton.PRIMARY) {
					GameManager.getInstance().queueInputs(true, KeyCode.F20);
				} else if (event.getButton() == MouseButton.SECONDARY) {
					GameManager.getInstance().queueInputs(true, KeyCode.F21);
				} else if (event.getButton() == MouseButton.MIDDLE) {
					GameManager.getInstance().queueInputs(true, KeyCode.F22);
				}
			} catch (MotorException e) {
				e.printStackTrace();
			}
		});
		gameView.setOnMouseMoved(event -> {
			GameManager.getInstance().setMousePosX(event.getX());
			GameManager.getInstance().setMousePosY(event.getY());
		});
	}

	public void showGameMenu(Stage stage) throws IOException {
		stage.setScene(menuScene);

		gm.pauseTheMotor();

		stage.show();
	}
	
	public void showRespawn(Stage stage) throws IOException {
		GameManager.getInstance().initializeRespawn();
		
		stage.setScene(respawnScene);

		stage.show();
	}	

	public void resume(Stage stage) throws IOException {
		gameView.widthProperty().bind(stackPane.widthProperty());
		gameView.heightProperty().bind(stackPane.heightProperty());
		stage.setScene(gameScene);

		gm.getGameViewWidth().bind(stackPane.widthProperty());
		gm.getGameViewHeight().bind(stackPane.heightProperty());

		gm.resumeTheMotor(gameView);

		keyInputHandling(gameScene, stage);

		stage.show();
	}

	public void start(Stage stage) throws IOException {
		gameView.widthProperty().bind(stackPane.widthProperty());
		gameView.heightProperty().bind(stackPane.heightProperty());
		stage.setScene(gameScene);

		gm.getGameViewWidth().bind(stackPane.widthProperty());
		gm.getGameViewHeight().bind(stackPane.heightProperty());

		gm.zuendTheMotorAn(gameView);

		keyInputHandling(gameScene, stage);

		stage.show();
	}
}