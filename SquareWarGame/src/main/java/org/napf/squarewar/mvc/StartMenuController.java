package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class StartMenuController {
	
    @FXML
    private Button startButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private Slider volumeSlider;   
    
    @FXML
    void startGame(ActionEvent event) throws IOException {
    	Stage stage;
       
    	stage = (Stage) startButton.getScene().getWindow();
//        View game = new GameView();
//        
//        Scene scene = new Scene(game);
//        stage.setScene(scene);
//        stage.show();
    	
		GameViewController gameViewController = new GameViewController();
		gameViewController.resume(stage);
    }

    @FXML
    void exitGame(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
}