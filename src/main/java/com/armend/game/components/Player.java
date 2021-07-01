package com.armend.game.components;

public abstract class Player {
	private final String name;
	protected Item previousItem;

	Player(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("The 'name' argument must be non-null and non-empty.");
		}
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Get the item that was previously chosen or null if there is none.
	 */
	public Item getPreviousItem() {
		return previousItem;
	}

	public abstract Item play();
}
