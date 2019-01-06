package com.armend.game.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerPlayerTest {

	@Test
	public void testNewComputerPlayerWithNullName() {
		try {
			new ComputerPlayer(null, null);
			fail("Should not have reached this point.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'name' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	public void testNewComputerPlayerWithNullInput() {
		try {
			new ComputerPlayer("Computer", null);
			fail("Should not have reached this point");
		} catch (NullPointerException e) {
			assertEquals("The 'input' argument must not be null.", e.getMessage());
		}
	}
}
