package com.armend.game.rules;

import com.armend.game.components.Item;

/**
 * Represents a set of rules for deciding the winner in the Rock-Paper-Scissors
 * game.
 * 
 * @author armend.hoxha
 *
 */
public interface DecisionRules {
	/**
	 * Decides which item is the winner and returns the winning item. If there is a
	 * tie, it will return null.
	 * 
	 * @return The item that is the winner or null if there is a tie between them.
	 */
	Item whoIsTheWinner(Item item1, Item item2);
}
