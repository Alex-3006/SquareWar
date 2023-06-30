package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameView extends View {

	public GameView() throws IOException {
	}

	void gameMenu() throws IOException {
		Stage stage;

		stage = (Stage) this.getScene().getWindow();
		View gameMenu = new GameMenuView();

		Scene scene = new Scene(gameMenu);
		stage.setScene(scene);
		stage.show();
	}

	void gameOver() throws IOException {
		Stage stage;

		stage = (Stage) this.getScene().getWindow();
		View gameOver = new GameOverView();

		Scene scene = new Scene(gameOver);
		stage.setScene(scene);
		stage.show();
	}

	void respawnScreen() throws IOException {
		Stage stage;

		stage = (Stage) this.getScene().getWindow();
		View respawn = new RespawnView();

		Scene scene = new Scene(respawn);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	void createGUI() throws IOException {
		getChildren().add(new Button("Settings") {
			@Override
			public void fire() {
				Stage stage;

				stage = (Stage) this.getScene().getWindow();
				View gameMenu = null;
				try {
					gameMenu = new GameMenuView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scene scene = new Scene(gameMenu);
				stage.setScene(scene);
				stage.show();
			}
		});

		getChildren().add(new Button("Respawn") {
			@Override
			public void fire() {
				Stage stage;

				stage = (Stage) this.getScene().getWindow();
				View respawn = null;
				try {
					respawn = new RespawnView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scene scene = new Scene(respawn);
				stage.setScene(scene);
				stage.show();
			}
		});

		getChildren().add(new Button("Game Over") {
			@Override
			public void fire() {
				Stage stage;

				stage = (Stage) this.getScene().getWindow();
				View gameOver = null;
				try {
					gameOver = new GameOverView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scene scene = new Scene(gameOver);
				stage.setScene(scene);
				stage.show();
			}
		});
	}
}
