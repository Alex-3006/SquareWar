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
        View gameMenu = new GameMenuView();
        
        Scene scene = new Scene(gameMenu);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exitGame(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
}
