package com.armend.game.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class HumanPlayerTest {
	@Test
	void testNewHumanPlayerWithNullName() {
		try {
			new HumanPlayer(null, null);
			fail("Should not have reached this point.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'name' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	void testNewHumanPlayerWithNullItemInput() {
		try {
			new HumanPlayer("John", null);
			fail("Should not have reached this ppint.");
		} catch (NullPointerException e) {
			assertEquals("The 'input' argument must not be null.", e.getMessage());
		}
	}

	@Test
	void testNewHumanPlayerWithEmptyName() {
		try {
			new HumanPlayer("", null);
		} catch (IllegalArgumentException e) {
			assertEquals("The 'name' argument must be non-null and non-empty.", e.getMessage());
		}
	}
}
