package com.armend.game.strategies;

import java.util.HashMap;
import java.util.Map;

import com.armend.game.components.Item;

/**
 * Defines the standard strategy of deciding who's the winner.
 * <ol>
 * <li>Paper beats Rock</li>
 * <li>Rock beats Scissors</li>
 * <li>Scissors beats Paper</li>
 * </ol>
 * 
 * @author armend.hoxha
 *
 */
public class StandardStrategy implements GameStrategy {

	private final Map<String, Integer> decisionTable;

	public StandardStrategy() {
		decisionTable = new HashMap<>();
		decisionTable.put(Item.Paper.name() + Item.Rock.name(), 1);
		decisionTable.put(Item.Rock.name() + Item.Paper.name(), 2);
		decisionTable.put(Item.Rock.name() + Item.Scissors.name(), 1);
		decisionTable.put(Item.Scissors.name() + Item.Rock.name(), 2);
		decisionTable.put(Item.Scissors.name() + Item.Paper.name(), 1);
		decisionTable.put(Item.Paper.name() + Item.Scissors.name(), 2);
		decisionTable.put(Item.Rock.name() + Item.Rock.name(), 0);
		decisionTable.put(Item.Paper.name() + Item.Paper.name(), 0);
		decisionTable.put(Item.Scissors.name() + Item.Scissors.name(), 0);
	}

	@Override
	public Item whoIsTheWinner(Item item1, Item item2) {
		if (item1 == null) {
			throw new IllegalArgumentException("item1 should not be null");
		}
		if (item2 == null) {
			throw new IllegalArgumentException("item2 should not be null");
		}

		Integer winner = decisionTable.get(item1.name() + item2.name());
		if (winner == null) {
			throw new RuntimeException(
					"Unable to decide who's the winner. Make sure you have included all the possibilites in the decisionTable.");
		}
		switch (winner) {
		case 0:
			return null;
		case 1:
			return item1;
		case 2:
			return item2;
		default:
			throw new RuntimeException("The value of 'winner' must be any of [0,1,2]; winner = " + winner);
		}
	}
}
