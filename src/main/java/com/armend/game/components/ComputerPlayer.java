package com.armend.game.components;

import java.util.Objects;

public class ComputerPlayer extends Player {

	private ItemInput input;

	/**
	 * Constructs a ComputerPlayer object with a RandomItemInput.
	 * 
	 * @param name Name of the player.
	 */
	public ComputerPlayer(String name) {
		this(name, new RandomItemInput());
	}

	/**
	 * Constructs a ComputerPlayer object with the given ItemInput.
	 * 
	 * @param name  Name of the player.
	 * @param input ItemInput from which items will be retrieved while playing the
	 *              game.
	 */
	public ComputerPlayer(String name, ItemInput input) {
		super(name);
		Objects.requireNonNull(input, "The 'input' argument must not be null.");
		this.input = input;
	}

	@Override
	public Item play() {
		previousItem = input.get();
		return previousItem;
	}
}
