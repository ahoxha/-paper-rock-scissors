package com.armend.game.components;

import java.util.function.BiFunction;

public enum Item {
	Rock(0), Paper(1), Scissors(2);

	private final int index;

	Item(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public static Item of(String input) {
		if (isEmptyOrNull(input)) {
			return null;
		}
		if (isOneLetter(input)) {
			return findItem(input, compareFirstLetterOnlyIgnoreCase());
		}
		return findItem(input, compareWholeStringIgnoreCase());
	}

	private static boolean isEmptyOrNull(String input) {
		return input == null || input.isEmpty();
	}

	private static boolean isOneLetter(String input) {
		return input.length() == 1;
	}

	private static BiFunction<String, String, Boolean> compareWholeStringIgnoreCase() {
		return (s1, s2) -> s1.equalsIgnoreCase(s2);
	}

	private static BiFunction<String, String, Boolean> compareFirstLetterOnlyIgnoreCase() {
		return (s1, s2) -> s1.toUpperCase().startsWith(s2.toUpperCase());
	}

	private static Item findItem(String input, BiFunction<String, String, Boolean> func) {
		for (Item item : Item.values()) {
			if (func.apply(item.name(), input)) {
				return item;
			}
		}
		return null;
	}
}
