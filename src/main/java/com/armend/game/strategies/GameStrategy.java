package com.armend.game.strategies;

import com.armend.game.components.Item;

/**
 * Represents a game strategy for playing the Rock-Paper-Scissors game.
 * 
 * @author armend.hoxha
 *
 */
public interface GameStrategy {
	/**
	 * Decides which item is the winner and returns the winning item. If there is a
	 * tie, it will return null.
	 * 
	 * @param item1 The first item.
	 * @param item2 The second item.
	 * @return The item that is the winner or null if there is a tie between them.
	 */
	Item whoIsTheWinner(Item item1, Item item2);
}
