package org.napf.squarewar.mvc;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StartMenuJpg extends Application {

    @Override
    public void start(Stage stage) {
    	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	double height = screenBounds.getHeight();
    	double width = screenBounds.getWidth();
        Image background = new Image(getClass().getResourceAsStream("Background.jpg"), width, height, false, false);
        ImageView imageView = new ImageView(background);
        
        Button button = new Button();
        button.setText("START");
        
        Group root = new Group(imageView, button);
        Scene scene = new Scene(new StackPane(root), 1920, 1080);
        scene.getStylesheets().add("style.css");
        button.getStyleClass().add("startButton");
        stage.setFullScreen(true);
        // stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}