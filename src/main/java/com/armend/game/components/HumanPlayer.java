package com.armend.game.components;

import java.util.Objects;

public class HumanPlayer extends Player {

	private ItemInput input;

	public HumanPlayer(String name, ItemInput input) {
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
