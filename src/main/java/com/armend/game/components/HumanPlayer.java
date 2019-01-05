package com.armend.game.components;

import com.armend.game.UserInput;

public class HumanPlayer extends Player {

	private UserInput input;

	public HumanPlayer(String name, UserInput input) {
		super(name);
		if (input == null) {
			throw new IllegalArgumentException("User input must not be null.");
		}
		this.input = input;
	}

	@Override
	public Item play() {
		previousItem = input.get();
		return previousItem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append("{name=").append(getName()).append("}");
		return builder.toString();
	}
}
