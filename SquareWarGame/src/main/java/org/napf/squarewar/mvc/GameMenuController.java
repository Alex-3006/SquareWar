package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameMenuController {

	@FXML
	private Button quitButton;

	@FXML
	private Button resumeButton;

	@FXML
	void quitGame(ActionEvent event) throws IOException {
		Stage stage;

		stage = (Stage) quitButton.getScene().getWindow();
		View startMenu = new StartMenuView();

		Scene scene = new Scene(startMenu);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void resumeGame(ActionEvent event) {

	}

}
