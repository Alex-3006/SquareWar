package org.napf.squarewar;
import org.napf.squarewar.core.GameManager;
import org.napf.squarewar.exceptions.MotorException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SquarewarApp extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        GameManager.getInstance().zuendTheMotorAn();
        
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
    }

    public static void main(String[] args) {
        launch();
    }

}