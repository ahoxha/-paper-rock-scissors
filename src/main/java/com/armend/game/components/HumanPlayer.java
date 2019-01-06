package com.armend.game.components;

import java.util.Objects;

import com.armend.game.ItemInput;

public class HumanPlayer extends Player {

	private ItemInput input;

	public HumanPlayer(String name, ItemInput input) {
		super(name);
		Objects.requireNonNull(input, "User input must not be null.");
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
