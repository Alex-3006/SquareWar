package org.napf.squarewar.mvc;

import javafx.scene.Parent;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class GameMenuView extends View{

	public GameMenuView() throws IOException {
	}

	@Override
    void createGUI() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("GameMenu.fxml"));
       getChildren().add(root);
    }
}
