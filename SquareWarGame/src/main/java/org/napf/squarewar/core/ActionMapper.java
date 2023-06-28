package org.napf.squarewar.core;

import java.util.ArrayList;

public class ActionMapper {

	private static ArrayList<InputAction> actions;
	
	static {
		actions = new ArrayList<InputAction>();
	}
	
	/**
	 * @param key the index of the key
	 * @return the InputAction with the corresponding key.
	 * Null if no corresponding InputAction is found.
	 */
	public static InputAction getAction(int key) {
		for (int i = 0; i< actions.size(); i++) {
			InputAction currInputAction = actions.get(i);
			if (currInputAction.getActionKey() == key) {
				return currInputAction;
			}
		}
		return null;
	}
	
}
