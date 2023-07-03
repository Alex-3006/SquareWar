package org.napf.squarewar;

import java.io.IOException;

import org.napf.squarewar.core.GameController;
import org.napf.squarewar.core.GameManager;
import org.napf.squarewar.mvc.GameMenuView;
import org.napf.squarewar.mvc.GameView;
import org.napf.squarewar.mvc.GameViewController;
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
import javafx.stage.Window;

public class SquarewarApp {		
	public void showGameMenu(Stage stage) throws IOException {
		View gameMenu = new GameMenuView();
		
		Stage secondStage = new Stage();
		secondStage.setTitle("Square War");
        secondStage.setResizable(false);
        secondStage.setX(0);
        secondStage.setY(0);
		secondStage.setScene(new Scene(gameMenu));
		secondStage.show();
	}
	
	public void quit() {
		 Window window =   ((Node)(event.getSource())).getScene().getWindow(); 
         if (window instanceof Stage){
             ((Stage) window).close();
         }
	}

	public void start(Stage stage) throws IOException {
		/*
		 * String javaVersion = System.getProperty("java.version"); String javafxVersion
		 * = System.getProperty("javafx.version"); Label l = new Label("Hello, JavaFX "
		 * + javafxVersion + ", running on Java " + javaVersion + ".");
		 */

		// Scene scene = new Scene(new StackPane(l), 640, 480);
		GameView gameView = new GameView();
		StackPane stackPane = new StackPane(gameView);
		// https://stackoverflow.com/questions/24533556/how-to-make-canvas-resizable-in-javafx
		gameView.widthProperty().bind(stackPane.widthProperty());
		gameView.heightProperty().bind(stackPane.heightProperty());
		Scene scene = new Scene(stackPane, 720, 640);
		stage.setScene(scene);

		GameManager.getInstance().getGameViewWidth().bind(stackPane.widthProperty());
		GameManager.getInstance().getGameViewHeight().bind(stackPane.heightProperty());

		GameManager.getInstance().zuendTheMotorAn(gameView);
		
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
		stage.show();
	}

}