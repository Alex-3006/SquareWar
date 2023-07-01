package org.napf.squarewar.mvc;

import java.io.IOException;

import javafx.scene.layout.Pane;

public abstract class View extends Pane{
	View() throws IOException {
        createGUI();
    }

    abstract void createGUI() throws IOException;
}
