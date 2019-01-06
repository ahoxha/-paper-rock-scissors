package com.armend.game;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.armend.game.components.ComputerPlayer;
import com.armend.game.strategies.GameStrategy;
import com.armend.game.strategies.StandardStrategy;

public class ArbiterTest {
	@Test
	public void testNonNullStrategy() {
		try {
			new Arbiter(null, null, null);
			fail("Should not have reached this point. All consturctor parameters must be non-null.");
		} catch (NullPointerException ne) {
			assertEquals("NULL value for 'strategy' is not allowed", ne.getMessage());
		}
	}

	@Test
	public void testNonNullPlayer1() {
		try {
			new Arbiter(new StandardStrategy(), null, null);
			fail("Should not have reached this point. All consturctor parameters must be non-null.");
		} catch (NullPointerException ne) {
			assertEquals("NULL value for 'player1' is not allowed", ne.getMessage());
		}
	}

	@Test
	public void testNonNullPlayer2() {
		try {
			new Arbiter(new StandardStrategy(), new ComputerPlayer("Computer"), null);
			fail("Should not have reached this point. All consturctor parameters must be non-null.");
		} catch (NullPointerException ne) {
			assertEquals("NULL value for 'player2' is not allowed", ne.getMessage());
		}
	}

	@Test
	public void testSetNullStrategy() {
		Arbiter arbiter = new Arbiter(new StandardStrategy(), new ComputerPlayer("Computer1"),
				new ComputerPlayer("Computer2"));
		arbiter.setStrategy(null);
		assertNotNull(arbiter.getStrategy());
	}

	@Test
	public void testSetNewStrategy() {
		GameStrategy strategy1 = new StandardStrategy();
		GameStrategy strategy2 = new StandardStrategy();
		Arbiter arbiter = new Arbiter(strategy1, new ComputerPlayer("C1"), new ComputerPlayer("C2"));
		assertEquals(strategy1, arbiter.getStrategy());
		arbiter.setStrategy(strategy2);
		assertEquals(strategy2, arbiter.getStrategy());
	}
}
