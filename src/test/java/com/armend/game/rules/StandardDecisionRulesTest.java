package com.armend.game.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.armend.game.components.Item;

class StandardDecisionRulesTest {

	private static DecisionRules strategy;

	@BeforeAll
	static void initializeStrategy() {
		strategy = new StandardDecisionRules();
	}

	@Test
	void testRockPaper() {
		assertEquals(Item.PAPER, strategy.whoIsTheWinner(Item.ROCK, Item.PAPER));
	}

	@Test
	void testPaperRock() {
		assertEquals(Item.PAPER, strategy.whoIsTheWinner(Item.PAPER, Item.ROCK));
	}

	@Test
	void testRockScissors() {
		assertEquals(Item.ROCK, strategy.whoIsTheWinner(Item.ROCK, Item.SCISSORS));
	}

	@Test
	void testScissorsRock() {
		assertEquals(Item.ROCK, strategy.whoIsTheWinner(Item.SCISSORS, Item.ROCK));
	}

	@Test
	void testScissorsPaper() {
		assertEquals(Item.SCISSORS, strategy.whoIsTheWinner(Item.SCISSORS, Item.PAPER));
	}

	@Test
	void testPaperScissors() {
		assertEquals(Item.SCISSORS, strategy.whoIsTheWinner(Item.PAPER, Item.SCISSORS));
	}

	@Test
	void testPaperPaper() {
		assertNull(strategy.whoIsTheWinner(Item.PAPER, Item.PAPER));
	}

	@Test
	void testRockRock() {
		assertNull(strategy.whoIsTheWinner(Item.ROCK, Item.ROCK));
	}

	@Test
	void testScissorsScissors() {
		assertNull(strategy.whoIsTheWinner(Item.SCISSORS, Item.SCISSORS));
	}

	@Test
	void testWithFirstNullItem() {
		try {
			strategy.whoIsTheWinner(null, Item.PAPER);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("firstItem should not be null", e.getMessage());
		}
	}

	@Test
	void testWithSecondNullItem() {
		try {
			strategy.whoIsTheWinner(Item.ROCK, null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("secondItem should not be null", e.getMessage());
		}
	}

	@Test
	void testWithBothNullItems() {
		try {
			strategy.whoIsTheWinner(null, null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("firstItem should not be null", e.getMessage());
		}
	}
}
