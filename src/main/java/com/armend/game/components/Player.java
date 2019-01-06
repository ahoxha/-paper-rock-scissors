package com.armend.game.components;

/**
 * Models an abstract player of the Paper-Rock-Scissors game. Concrete
 * subclasses of this class have to implement the 'play()' method.
 * 
 * @author armend.hoxha
 *
 */
public abstract class Player {
	private String name;
	protected Item previousItem;

	public Player(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("The 'name' argument must be non-null and non-empty.");
		}
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Get the item that was chosen previously or null if there is none.
	 * 
	 * @return {@link Item} or null.
	 */
	public Item getPreviousItem() {
		return previousItem;
	}

	/**
	 * Make the move.
	 * 
	 * @return An Item object that you want choose for a given round.
	 */
	public abstract Item play();
}
