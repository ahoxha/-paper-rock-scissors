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
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Get the item that was chosen previously.
	 * 
	 * @return {@link Item}
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
