package org.napf.squarewar.mvc;

import java.io.IOException;

import org.napf.squarewar.core.GameManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RespawnController {
	@FXML
	private Button respawnButton;

	@FXML
	void respawn(ActionEvent event) throws IOException {
		if (GameManager.getInstance().respawnEnd() == true) {
			Stage stage;

			stage = (Stage) respawnButton.getScene().getWindow();
			
			GameViewController gameViewController = new GameViewController();
			gameViewController.resume(stage);
			
			GameManager.getInstance().resetRespawn();
		}
		else {
			System.out.println("here");
		}
	}
}
