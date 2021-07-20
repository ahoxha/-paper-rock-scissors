package com.armend.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.armend.game.components.ComputerPlayer;
import com.armend.game.rules.StandardDecisionRules;

class ImpatientArbiterTest {
	@Test
	void testNewImpatientArbiterWithNullDecisionRules() {
		try {
			new ImpatientArbiter(null, null, null, 1);
			fail("Should not have reached this point. All constructor parameters must be non-null.");
		} catch (NullPointerException e) {
			assertEquals("NULL value for 'decisionRules' is not allowed", e.getMessage());
		}
	}

	@Test
	void testNewImpatientArbiterWithNullFirstPlayer() {
		try {
			new ImpatientArbiter(new StandardDecisionRules(), null, null, 1);
			fail("Should not have reached this point. All constructor parameters must be non-null.");
		} catch (NullPointerException e) {
			assertEquals("NULL value for 'player1' is not allowed", e.getMessage());
		}
	}

	@Test
	void testNewImpatientArbiterWithNullSecondPlayer() {
		try {
			new ImpatientArbiter(new StandardDecisionRules(), new ComputerPlayer("computer1"), null, 1);
			fail("Should not have reached this point. All constructor parameters must be non-null.");
		} catch (NullPointerException e) {
			assertEquals("NULL value for 'player2' is not allowed", e.getMessage());
		}
	}

	@Test
	void testNewImpatientArbiterWithNegativeTimeToWait() {
		ComputerPlayer player1 = new ComputerPlayer("Computer1");
		ComputerPlayer player2 = new ComputerPlayer("Computer2");
		ImpatientArbiter arbiter = new ImpatientArbiter(new StandardDecisionRules(), player1, player2, -2);
		assertEquals(4, arbiter.getSecondsToWait());
	}

	@Test
	void testNewImpatientArbiterWithPositiveTimeToWait() {
		ComputerPlayer player1 = new ComputerPlayer("Computer1");
		ComputerPlayer player2 = new ComputerPlayer("Computer2");
		ImpatientArbiter arbiter = new ImpatientArbiter(new StandardDecisionRules(), player1, player2, 6);
		assertEquals(6, arbiter.getSecondsToWait());
	}
}
