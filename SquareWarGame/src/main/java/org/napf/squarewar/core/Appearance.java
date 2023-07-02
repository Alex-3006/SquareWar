package org.napf.squarewar.core;

import java.util.ArrayList;
import java.util.Arrays;

public class Appearance {

	private ArrayList<AppearanceComponent> components;
	
	private GameObject go;
	
	public Appearance(GameObject gameObject, AppearanceComponent... components) {
		go = gameObject;
		this.components = new ArrayList<AppearanceComponent>(Arrays.asList(components));
	}
	
	public Appearance(GameObject gameObject) {
		this(gameObject, new AppearanceComponent[0]);
	}
	
	public AppearanceComponent[] getComponents() {
		return (AppearanceComponent[])components.toArray(new AppearanceComponent[components.size()]);
	}
	
	public void addComponent(AppearanceComponent component) {
		components.add(component);
	}
	
	public void removeComponent(AppearanceComponent component) {
		components.remove(component);
	}
	
	/**
	 * Gets the Bounding Box of the Appearance in world space (xOffset & yOffset stand for coordinates in world space).
	 * @return the bounding box of the Appearance in world space.
	 */
	public BoundingBox getBoundingBox() {
		if (components.isEmpty()) {
			return new BoundingBox(0, 0, 0, 0);
		}
		
		double leftmostPoint = 0, uppermostPoint = 0, rightmostPoint = 0, lowermostPoint = 0;
		
		for (int i = 0; i < components.size(); i++) {
			BoundingBox bb = components.get(i).getBoundingBox();
			double leftEdge = bb.getxOffset() - bb.getWidth() / 2;
			double rightEdge = bb.getxOffset() + bb.getWidth() / 2;
			double upperEdge = bb.getyOffset() - bb.getHeight() / 2;
			double lowerEdge = bb.getyOffset() + bb.getHeight() / 2;
			
			if (i == 0) {
				leftmostPoint = leftEdge;
				rightmostPoint = rightEdge;
				uppermostPoint = upperEdge;
				lowermostPoint = lowerEdge;
			} else {
				if (leftEdge < leftmostPoint)
					leftmostPoint = leftEdge;
				if (rightEdge > rightmostPoint)
					rightmostPoint = rightEdge;
				if (upperEdge < uppermostPoint)
					uppermostPoint = upperEdge;
				if (lowerEdge > lowermostPoint)
					lowermostPoint = lowerEdge;
			}
		}
		
		return new BoundingBox(rightmostPoint - leftmostPoint, lowermostPoint - uppermostPoint, go.getXpos(), go.getYpos());
	}
}
