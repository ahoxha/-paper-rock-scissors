package com.armend.game.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.armend.game.components.Item;

class StandardDecisionRulesTest {

	private final DecisionRules rules = new StandardDecisionRules();

	@Test
	void testRockPaper() {
		assertEquals(Item.PAPER, rules.whoIsTheWinner(Item.ROCK, Item.PAPER));
	}

	@Test
	void testPaperRock() {
		assertEquals(Item.PAPER, rules.whoIsTheWinner(Item.PAPER, Item.ROCK));
	}

	@Test
	void testRockScissors() {
		assertEquals(Item.ROCK, rules.whoIsTheWinner(Item.ROCK, Item.SCISSORS));
	}

	@Test
	void testScissorsRock() {
		assertEquals(Item.ROCK, rules.whoIsTheWinner(Item.SCISSORS, Item.ROCK));
	}

	@Test
	void testScissorsPaper() {
		assertEquals(Item.SCISSORS, rules.whoIsTheWinner(Item.SCISSORS, Item.PAPER));
	}

	@Test
	void testPaperScissors() {
		assertEquals(Item.SCISSORS, rules.whoIsTheWinner(Item.PAPER, Item.SCISSORS));
	}

	@Test
	void testPaperPaper() {
		assertNull(rules.whoIsTheWinner(Item.PAPER, Item.PAPER));
	}

	@Test
	void testRockRock() {
		assertNull(rules.whoIsTheWinner(Item.ROCK, Item.ROCK));
	}

	@Test
	void testScissorsScissors() {
		assertNull(rules.whoIsTheWinner(Item.SCISSORS, Item.SCISSORS));
	}

	@Test
	void testWithFirstNullItem() {
		try {
			rules.whoIsTheWinner(null, Item.PAPER);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("firstItem should not be null", e.getMessage());
		}
	}

	@Test
	void testWithSecondNullItem() {
		try {
			rules.whoIsTheWinner(Item.ROCK, null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("secondItem should not be null", e.getMessage());
		}
	}

	@Test
	void testWithBothNullItems() {
		try {
			rules.whoIsTheWinner(null, null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("firstItem should not be null", e.getMessage());
		}
	}
}
