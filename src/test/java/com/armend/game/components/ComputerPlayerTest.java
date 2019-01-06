package com.armend.game.components;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerPlayerTest {

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
