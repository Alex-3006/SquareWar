package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GameOverView extends View {

	public GameOverView() throws IOException {
	}

	@Override
	void createGUI() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
		getChildren().add(root);
	}

}