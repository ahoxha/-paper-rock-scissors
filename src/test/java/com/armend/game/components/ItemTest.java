package com.armend.game.components;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ItemTest {
	@ParameterizedTest(name = "Convert String to Item {index}")
	@ValueSource(strings = { "Scissors", "Rock", "Paper", "scissors", "rock", "paper", "S", "R", "P", "s", "r", "p" })
	public void testOf(String value) {
		assertNotNull(Item.of(value));
	}

	@Test
	public void testOfWithNull() {
		assertNull(Item.of(null));
	}

	@Test
	public void testOfWithEmptyString() {
		assertNull(Item.of(""));
	}

	@Test
	public void testOfWithWrongNonEmptyString() {
		assertNull(Item.of("Bla Bla"));
	}

	@Test
	public void ensureIndices() {
		assertEquals(0, Item.Rock.getIndex(), Item.Rock + " must have index = 0");
		assertEquals(1, Item.Paper.getIndex(), Item.Paper + " must have index = 1");
		assertEquals(2, Item.Scissors.getIndex(), Item.Scissors + " must have index = 2");
	}
}
