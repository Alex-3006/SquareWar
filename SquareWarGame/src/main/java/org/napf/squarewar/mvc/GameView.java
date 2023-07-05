package org.napf.squarewar.mvc;

import java.io.IOException;
import org.napf.squarewar.core.Appearance;
import org.napf.squarewar.core.AppearanceComponent;
import org.napf.squarewar.core.BoundingBox;
import org.napf.squarewar.core.Camera;
import org.napf.squarewar.core.GameObject;
import org.napf.squarewar.core.Rectangle;
import org.napf.squarewar.mvc.GameOverView;

import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameView extends Canvas {
	
	/**
	 * World-To-Canvas-Factor (Conversion factor between world space and canvas space
	 */
	private double wtcf;
	private double camWidth;
	private boolean isDebugMode;
	
	private GraphicsContext gc;
	private Camera cam;
	
	public GameView() {
		gc = getGraphicsContext2D();
	}
	
	public void prepare(Camera camera) {
		cam = camera;
		camWidth = getWidth() / getHeight() * cam.getHeight();
		wtcf = getHeight() / cam.getHeight();
	}
	
	/**
	 * Draws all objects provided to the method on the GameView canvas
	 * @param gameObjects all GameObjects that can be drawn to the canvas if visible
	 */
	public void render(GameObject[] gameObjects) {
		boolean[] gameObjectsInRect = new boolean[gameObjects.length];
		for (int i = 0; i < gameObjects.length; i++) {
			gameObjectsInRect[i] = isInRect(gameObjects[i]);
			if (gameObjectsInRect[i]) {
				drawGameObject(gameObjects[i]);
			}
		}
		for (int i = 0; i < gameObjects.length; i++) {
			if (isDebugMode && gameObjectsInRect[i]) {
				drawDebugLabel(gameObjects[i]);
			}
		}
		if (isDebugMode)
			drawDebugGrid();
	}
	
	public void clear() {
		gc.clearRect(0, 0, getWidth(), getHeight());
	}
	
	public boolean isInRect(GameObject gameObject) {
		BoundingBox bb = gameObject.getAppearance().getBoundingBox();
		double bbleft = bb.getxOffset() - bb.getWidth() / 2, bbright = bb.getxOffset() + bb.getWidth() / 2;
		double bbup = bb.getyOffset() - bb.getHeight() / 2, bblow = bb.getyOffset() + bb.getHeight() / 2;
		
		//A bounding box is not inside the cam rect if all of its edge points are either left, right, higher or lower to the cam rect edges.
		double camleft = cam.getXpos() - camWidth / 2, camright = cam.getXpos() + camWidth / 2;
		double camup = cam.getYpos() - cam.getHeight() / 2, camlow = cam.getYpos() + cam.getHeight() / 2;
		
		//Edge left
		if (bbleft < camleft && bbright < camleft) {
			return false;
		}
		if (bbleft > camright && bbright > camright) {
			return false;
		}
		if (bbup < camup && bblow < camup) {
			return false;
		}
		if (bbup > camlow && bblow > camlow) {
			return false;
		}
		
		return true;
	}
	
	public double worldToCanvasSpaceX(double worldX) {
		return (getWidth() / 2 + (worldX - cam.getXpos()) * wtcf);
	}
	public double worldToCanvasSpaceY(double worldY) {
		return (getHeight() / 2 + (worldY - cam.getYpos()) * wtcf);
	}
	
	public double wtcX(double x) {
		return worldToCanvasSpaceX(x);
	}
	public double wtcY(double y) {
		return worldToCanvasSpaceY(y);
	}
	
	private void drawGameObject(GameObject go) {
		AppearanceComponent[] comps = go.getAppearance().getComponents();
		
		for (int i = 0; i < comps.length; i++) {
			double objX = go.getXpos();
			double objY = go.getYpos();
			
			if (comps[i] instanceof Rectangle) {
				drawRectangle(objX, objY, (Rectangle)comps[i]);
			}
		}
	}
	
	private void drawRectangle(double objXPos, double objYPos, Rectangle r) {
		gc.setFill(r.getColor());
		gc.fillRect(worldToCanvasSpaceX(objXPos + r.getxOffset() - r.getWidth() / 2), worldToCanvasSpaceY(objYPos + r.getyOffset() - r.getHeight() / 2), r.getWidth() * wtcf, r.getHeight() * wtcf);
	}
	
	/**
	 * Additionally draws a coordinate grid as well as GameObject information onto the canvas
	 */
	private void drawDebugGrid() {
		//coordinate grid
		gc.setStroke(Color.DARKGRAY);
		gc.setLineWidth(1);
		double camLeftEdge = cam.getXpos() - camWidth / 2;
		double camRightEdge = cam.getXpos() + camWidth / 2;
		double camUpperEdge = cam.getYpos() - cam.getHeight() / 2;
		double camLowerEdge = cam.getYpos() + cam.getHeight() / 2;
		double linePos = Math.ceil(camLeftEdge);
		//Draw vertical lines
		while (linePos < camRightEdge) {
			gc.strokeLine(wtcX(linePos), wtcY(camUpperEdge), wtcX(linePos), wtcY(camLowerEdge));
			linePos++;
		}
		//Draw horizontal lines
		linePos = Math.ceil(camUpperEdge);
		while (linePos < camLowerEdge) {
			gc.strokeLine(wtcX(camLeftEdge), wtcY(linePos), wtcX(camRightEdge), wtcY(linePos));
			linePos++;
		}
		
		//Red and green origin axes
		gc.setLineWidth(2);
		gc.setStroke(Color.LIGHTGREEN); // Pos x
		gc.strokeLine(wtcX(0), wtcY(0), wtcX(Math.max(0, camRightEdge)), wtcY(0));
		gc.setStroke(Color.DARKGREEN); // Neg x
		gc.strokeLine(wtcX(0), wtcY(0), wtcX(Math.min(0, camLeftEdge)), wtcY(0));
		gc.setStroke(Color.PALEVIOLETRED); // Pos y
		gc.strokeLine(wtcX(0), wtcY(0), wtcX(0), wtcY(Math.min(0, camUpperEdge)));
		gc.setStroke(Color.DARKRED); // Neg y
		gc.strokeLine(wtcX(0), wtcY(0), wtcX(0), wtcY(Math.max(0, camLowerEdge)));
	}
	
	private void drawDebugLabel(GameObject go) {
		// GameObject info (Pos, Name)
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(new Font(gc.getFont().getName(), 16));
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(0.5);
		double textPosX = go.getXpos(), textPosY = go.getYpos();
		// %.2f = 2 Nachkommastellen (per ChatGPT)
		String text = "( " + String.format("%.2f", go.getXpos()) + " | " + String.format("%.2f", go.getYpos()) + " )\n" + go.getName();
		gc.fillText(text, wtcX(textPosX), wtcY(textPosY));
		gc.strokeText(text, wtcX(textPosX), wtcY(textPosY));
	}
	

	public boolean isDebugMode() {
		return isDebugMode;
	}

	public void setDebugMode(boolean isDebugMode) {
		this.isDebugMode = isDebugMode;
	}

}