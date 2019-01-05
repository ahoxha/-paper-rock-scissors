package com.armend.game.components;

import java.security.SecureRandom;

public class ComputerPlayer extends Player {

	private SecureRandom random;
	private Item[] components;

	public ComputerPlayer(String name) {
		super(name);
		random = new SecureRandom();
		components = Item.values();
	}

	@Override
	public Item play() {
		previousItem = components[random.nextInt(components.length)];
		return previousItem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append("{name=").append(getName()).append("}");
		return builder.toString();
	}
}
