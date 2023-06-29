package org.napf.squarewar.core;

import java.util.ArrayList;

public class Appearance {

	private ArrayList<AppearanceComponent> components;
	
	public AppearanceComponent[] getComponents() {
		return (AppearanceComponent[])components.toArray(new AppearanceComponent[components.size()]);
	}
	
	public void addComponent(AppearanceComponent component) {
		components.add(component);
	}
	
	public void removeComponent(AppearanceComponent component) {
		components.remove(component);
	}
}
