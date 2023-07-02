package org.napf.squarewar;
import org.napf.squarewar.core.GameManager;
import org.napf.squarewar.mvc.GameView;
import org.napf.squarewar.exceptions.MotorException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SquarewarApp extends Application {

    @Override
    public void start(Stage stage) {
        /*
    	String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        */
    	
        //Scene scene = new Scene(new StackPane(l), 640, 480);
    	GameView gameView = new GameView();
    	StackPane stackPane = new StackPane(gameView);
    	//https://stackoverflow.com/questions/24533556/how-to-make-canvas-resizable-in-javafx
    	gameView.widthProperty().bind(stackPane.widthProperty());
    	gameView.heightProperty().bind(stackPane.heightProperty());
    	Scene scene = new Scene(stackPane, 640, 480);
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

    public static void main(String[] args) {
        launch();
    }

}