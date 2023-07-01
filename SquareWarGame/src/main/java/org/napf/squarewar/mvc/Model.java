package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Model extends Application {
	
    @Override
    public void start(Stage primaryStage) throws IOException {
        View startMenu = new StartMenuView();

        Scene scene = new Scene(startMenu, 720, 640);
        
        primaryStage.setTitle("Square War");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
