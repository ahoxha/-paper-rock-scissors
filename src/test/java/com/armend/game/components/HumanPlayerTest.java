package com.armend.game.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HumanPlayerTest {
	@Test
	public void testNewHumanPlayerWithNullName() {
		try {
			new HumanPlayer(null, null);
			fail("Should not have reached this point.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'name' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	public void testNewHumanPlayerWithNullItemInput() {
		try {
			new HumanPlayer("John", null);
			fail("Should not have reached this ppint.");
		} catch (NullPointerException e) {
			assertEquals("The 'input' argument must not be null.", e.getMessage());
		}
	}
}
