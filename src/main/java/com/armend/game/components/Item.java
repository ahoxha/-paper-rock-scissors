package com.armend.game.components;

import java.util.function.BiPredicate;

public enum Item {
    ROCK(0), PAPER(1), SCISSORS(2);

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

    private static BiPredicate<String, String> compareWholeStringIgnoreCase() {
        return String::equalsIgnoreCase;
    }

    private static BiPredicate<String, String> compareFirstLetterOnlyIgnoreCase() {
        return (s1, s2) -> s1.toUpperCase().startsWith(s2.toUpperCase());
    }

    private static Item findItem(String input, BiPredicate<String, String> predicate) {
        for (Item item : Item.values()) {
            if (predicate.test(item.name(), input)) {
                return item;
            }
        }
        return null;
    }
}
