package org.napf.squarewar.mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartMenu extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Image background = new Image("/home/alex/git/SquareWar/SquareWarGame/assets/Background.jpg");
        ImageView imageView = new ImageView(background); 
        Group root = new Group(imageView);
        Scene scene = new Scene(new StackPane(root), 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}