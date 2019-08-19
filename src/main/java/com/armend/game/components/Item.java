package com.armend.game.components;

/**
 * Specifies the items for the Paper - Rock Scissors game.
 * 
 * @author armend.hoxha
 *
 */
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
	 * Return the item that 's' matches with (case insensitive).
	 * <p>
	 * If 's' is a single letter, then:
	 * <ul>
	 * <li>return Paper if s= {P | p}</li>
	 * <li>return Rock if s = {R | r}</li>
	 * <li>return Scissors if s = {S | s}</li>
	 * <li>return null if none of the above is true</li>
	 * </ul>
	 * 
	 * @param s
	 * @return The item that matches with 's' or null if none matches.
	 */
	public static Item of(String s) {
		if (s == null || s.isEmpty()) {
			return null;
		}
		if (s.length() > 1) {
			for (Item item : Item.values()) {
				if (item.name().equalsIgnoreCase(s.toUpperCase())) {
					return item;
				}
			}
		} else {
			if (s.equalsIgnoreCase("P")) {
				return Paper;
			}
			if (s.equalsIgnoreCase("R")) {
				return Rock;
			}
			if (s.equalsIgnoreCase("S")) {
				return Scissors;
			}
		}
		return null;
	}
}
