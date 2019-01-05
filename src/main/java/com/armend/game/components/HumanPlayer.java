package com.armend.game.components;

public class HumanPlayer extends AbstractPlayer {

	public HumanPlayer(String name) {
		super(name);
	}

	@Override
	public Item play() {
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append("{name=").append(getName()).append("}");
		return builder.toString();
	}
}
