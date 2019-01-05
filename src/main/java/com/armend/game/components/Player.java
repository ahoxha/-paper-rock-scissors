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

	public Item getPreviousItem() {
		return previousItem;
	}

	public abstract Item play();
}
