package org.napf.squarewar.core;

import java.util.ArrayList;
import java.util.Arrays;

import org.napf.squarewar.exceptions.ActionMapperException;

import javafx.scene.input.KeyCode;

public class ActionMapper {

	private static ArrayList<InputAction> actions;

	static {
		actions = new ArrayList<InputAction>();
		actions.add(new InputAction("MoveLeft", KeyCode.A, InputActionState.Press));
		actions.add(new InputAction("MoveUp", KeyCode.W, InputActionState.Press));
		actions.add(new InputAction("MoveRight", KeyCode.D, InputActionState.Press));
		actions.add(new InputAction("MoveDown", KeyCode.S, InputActionState.Press));
		actions.add(new InputAction("SpaceDown", KeyCode.SPACE, InputActionState.Down));
		actions.add(new InputAction("SpacePress", KeyCode.SPACE, InputActionState.Press));
		actions.add(new InputAction("SpaceUp", KeyCode.SPACE, InputActionState.Up));
		actions.add(new InputAction("DebugToggle", KeyCode.F1, InputActionState.Down));
		actions.add(new InputAction("CamMoveLeft", KeyCode.LEFT, InputActionState.Press));
		actions.add(new InputAction("CamMoveUp", KeyCode.UP, InputActionState.Press));
		actions.add(new InputAction("CamMoveRight", KeyCode.RIGHT, InputActionState.Press));
		actions.add(new InputAction("CamMoveDown", KeyCode.DOWN, InputActionState.Press));
		actions.add(new InputAction("LeftMouseDown", KeyCode.F20, InputActionState.Down));
		actions.add(new InputAction("RightMouseDown", KeyCode.F21, InputActionState.Down));

	}

	/**
	 * Finds the InputAction that is associated with the provided key.
	 * @param key the KeyCode of the key
	 * @param state the InputActionState of the key
	 * @return the InputAction with the corresponding key and state. If multiple InputActions are associated with the given key, only the one found first is returned.
	 * @throws ActionMapperException if the key has no associated InputAction
	 */
	public static InputAction getAction(KeyCode key, InputActionState state) throws ActionMapperException {
		for (int i = 0; i < actions.size(); i++) {
			InputAction currInputAction = actions.get(i);
			if (currInputAction.getActionKey() == key && currInputAction.getActionState() == state) {
				return currInputAction;
			}
		}
		throw new ActionMapperException("No InputAction with KeyCode " + key.toString() + " found!");
	}

	/**
	 * Finds the InputActions that are associated with the provided key.
	 * @param key the KeyCode of the key
	 * @param state the InputActionState of the key
	 * @return the InputActions with the corresponding key and state
	 * @throws ActionMapperException if the key has no associated InputAction
	 */
	public static InputAction[] getActions(KeyCode key, InputActionState state) throws ActionMapperException {
		ArrayList<InputAction> foundInputActions = new ArrayList<InputAction>();
		for (int i = 0; i < actions.size(); i++) {
			InputAction currInputAction = actions.get(i);
			if (currInputAction.getActionKey() == key && currInputAction.getActionState() == state) {
				foundInputActions.add(currInputAction);
			}
		}
		if (foundInputActions.size() > 0) {
			return (InputAction[]) foundInputActions.toArray(new InputAction[foundInputActions.size()]);
		} else {
			throw new ActionMapperException("No InputActions with KeyCode " + key.toString() + " found!");
		}
	}

}