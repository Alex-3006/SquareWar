package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class RespawnView extends View {

	public RespawnView() throws IOException {
	}

	@Override
    void createGUI() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("Respawn.fxml"));
       getChildren().add(root);
    }

}