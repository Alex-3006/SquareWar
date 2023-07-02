package org.napf.squarewar.core;

import javafx.scene.input.KeyCode;

public class InputAction {
	private String actionName;
	private KeyCode actionKey;
	private InputActionState actionState;
	
	public InputAction(String name, KeyCode key, InputActionState state) {
		actionName = name;
		actionKey = key;
		actionState = state;
	}
	
	public String getActionName() {
		return actionName;
	}

	public KeyCode getActionKey() {
		return actionKey;
	}

	public InputActionState getActionState() {
		return actionState;
	}
}
