package com.armend.game.components;

/**
 * Models an abstract player of the Paper-Rock-Scissors game. Concrete
 * subclasses of this class have to implement the 'play()' method.
 * 
 * @author armend.hoxha
 *
 */
public abstract class AbstractPlayer {
	private String name;

	public AbstractPlayer(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public abstract Item play();
}
