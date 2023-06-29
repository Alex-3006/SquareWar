package org.napf.squarewar.mvc;

import javafx.scene.Parent;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class StartMenuView extends View {
	public StartMenuView() throws IOException {
		super();
	}

	@Override
    void createGUI() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
       getChildren().add(root);
    }
}
