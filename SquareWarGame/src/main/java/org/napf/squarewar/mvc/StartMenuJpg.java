package org.napf.squarewar.mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartMenuJpg extends Application {

    @Override
    public void start(Stage stage) {
        Image background = new Image(getClass().getResourceAsStream("Background.jpg"), 720, 640, true, false);
        ImageView imageView = new ImageView(background);
        
        Button startButton = new Button();
        startButton.setText("START");
        
        Button exitButton = new Button();
        exitButton.setText("EXIT");

        final Label volumeLabel = new Label("Volume");
        
        Slider volumeSlider = new Slider(0, 1, 0.3);
        
        Group root = new Group(imageView, startButton, exitButton, volumeSlider, volumeLabel);
        Scene scene = new Scene(new StackPane(root), 720, 640);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        startButton.getStyleClass().add("startButton");
        exitButton.getStyleClass().add("exitButton");
        volumeLabel.getStyleClass().add("volumeLabel");
        volumeSlider.getStyleClass().add("volumeSlider");
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}