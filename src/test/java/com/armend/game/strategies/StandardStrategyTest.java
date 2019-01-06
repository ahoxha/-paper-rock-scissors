package com.armend.game.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.armend.game.components.Item;

public class StandardStrategyTest {

	private static GameStrategy strategy;

	@BeforeAll
	public static void initializeStrategy() {
		strategy = new StandardStrategy();
	}

	@Test
	public void testRockPaper() {
		assertEquals(Item.Paper, strategy.whoIsTheWinner(Item.Rock, Item.Paper));
	}

	@Test
	public void testPaperRock() {
		assertEquals(Item.Paper, strategy.whoIsTheWinner(Item.Paper, Item.Rock));
	}

	@Test
	public void testRockScissors() {
		assertEquals(Item.Rock, strategy.whoIsTheWinner(Item.Rock, Item.Scissors));
	}

	@Test
	public void testScissorsRock() {
		assertEquals(Item.Rock, strategy.whoIsTheWinner(Item.Scissors, Item.Rock));
	}

	@Test
	public void testScissorsPaper() {
		assertEquals(Item.Scissors, strategy.whoIsTheWinner(Item.Scissors, Item.Paper));
	}

	@Test
	public void testPaperScissors() {
		assertEquals(Item.Scissors, strategy.whoIsTheWinner(Item.Paper, Item.Scissors));
	}

	@Test
	public void testPaperPaper() {
		assertNull(strategy.whoIsTheWinner(Item.Paper, Item.Paper));
	}

	@Test
	public void testRockRock() {
		assertNull(strategy.whoIsTheWinner(Item.Rock, Item.Rock));
	}

	@Test
	public void testScissorsScissors() {
		assertNull(strategy.whoIsTheWinner(Item.Scissors, Item.Scissors));
	}

	@Test
	public void testWithFirstNullItem() {
		try {
			strategy.whoIsTheWinner(null, Item.Paper);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("item1 should not be null", e.getMessage());
		}
	}

	@Test
	public void testWithSecondNullItem() {
		try {
			strategy.whoIsTheWinner(Item.Rock, null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("item2 should not be null", e.getMessage());
		}
	}

	@Test
	public void testWithBothNullItems() {
		try {
			strategy.whoIsTheWinner(null, null);
			fail("Should have thrown an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("item1 should not be null", e.getMessage());
		}
	}
}
