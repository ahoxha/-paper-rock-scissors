package com.armend.game.components;

import java.security.SecureRandom;

public class ComputerPlayer extends AbstractPlayer {

	private SecureRandom random;
	private Item[] components;

	public ComputerPlayer(String name) {
		super(name);
		random = new SecureRandom();
		components = Item.values();
	}

	@Override
	public Item play() {
		return components[random.nextInt(components.length)];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append("{name=").append(getName()).append("}");
		return builder.toString();
	}
}
