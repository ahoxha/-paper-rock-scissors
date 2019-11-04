package com.armend.game.components;

public enum Item {
	Rock(0), Paper(1), Scissors(2);

	private final int index;

	Item(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	/**
	 * Returns the item that matches with 's' or null if none matches. (case
	 * insensitive).
	 * <p>
	 * If 's' is a single letter, then:
	 * <ul>
	 * <li>return Paper if s= {P | p}</li>
	 * <li>return Rock if s = {R | r}</li>
	 * <li>return Scissors if s = {S | s}</li>
	 * <li>return null if none of the above is true</li>
	 * </ul>
	 */
	public static Item of(String input) {
		if (input == null || input.isEmpty()) {
			return null;
		}
		if (input.length() == 1) {
			return findItemByMatchingTheFirstLetter(input);
		}
		return findItemByIteratingOverValues(input);
	}

	private static Item findItemByMatchingTheFirstLetter(String input) {
		for (Item item : Item.values()) {
			if (item.name().startsWith(input.toUpperCase())) {
				return item;
			}
		}
		return null;
	}

	private static Item findItemByIteratingOverValues(String input) {
		for (Item item : Item.values()) {
			if (item.name().equalsIgnoreCase(input.toUpperCase())) {
				return item;
			}
		}
		return null;
	}
}
