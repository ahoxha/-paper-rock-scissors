package com.armend.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.armend.game.components.ComputerPlayer;
import com.armend.game.strategies.StandardStrategy;

public class ArbiterTest {
	@Test
	public void testNonNullStrategy() {
		try {
			new Arbiter(null, null, null);
			Assertions.fail("Should not have reached this point. All consturctor parameters must be non-null.");
		} catch (NullPointerException ne) {
			Assertions.assertEquals("NULL value for 'strategy' is not allowed", ne.getMessage());
		}
	}

	@Test
	public void testNonNullPlayer1() {
		try {
			new Arbiter(new StandardStrategy(), null, null);
			Assertions.fail("Should not have reached this point. All consturctor parameters must be non-null.");
		} catch (NullPointerException ne) {
			Assertions.assertEquals("NULL value for 'player1' is not allowed", ne.getMessage());
		}
	}

	@Test
	public void testNonNullPlayer2() {
		try {
			new Arbiter(new StandardStrategy(), new ComputerPlayer("Computer"), null);
			Assertions.fail("Should not have reached this point. All consturctor parameters must be non-null.");
		} catch (NullPointerException ne) {
			Assertions.assertEquals("NULL value for 'player2' is not allowed", ne.getMessage());
		}
	}

	@Test
	public void testSetNullStrategy() {
		Arbiter arbiter = new Arbiter(new StandardStrategy(), new ComputerPlayer("Computer1"),
				new ComputerPlayer("Computer2"));
		arbiter.setStrategy(null);
		Assertions.assertNotNull(arbiter.getStrategy());
	}
}
