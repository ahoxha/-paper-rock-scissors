package com.armend.game.strategies;

import org.junit.jupiter.api.Assertions;
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
		Assertions.assertEquals(Item.Paper, strategy.whoIsTheWinner(Item.Rock, Item.Paper));
	}

	@Test
	public void testPaperRock() {
		Assertions.assertEquals(Item.Paper, strategy.whoIsTheWinner(Item.Paper, Item.Rock));
	}

	@Test
	public void testRockScissors() {
		Assertions.assertEquals(Item.Rock, strategy.whoIsTheWinner(Item.Rock, Item.Scissors));
	}

	@Test
	public void testScissorsRock() {
		Assertions.assertEquals(Item.Rock, strategy.whoIsTheWinner(Item.Scissors, Item.Rock));
	}

	@Test
	public void testScissorsPaper() {
		Assertions.assertEquals(Item.Scissors, strategy.whoIsTheWinner(Item.Scissors, Item.Paper));
	}

	@Test
	public void testPaperScissors() {
		Assertions.assertEquals(Item.Scissors, strategy.whoIsTheWinner(Item.Paper, Item.Scissors));
	}

	@Test
	public void testPaperPaper() {
		Assertions.assertNull(strategy.whoIsTheWinner(Item.Paper, Item.Paper));
	}

	@Test
	public void testRockRock() {
		Assertions.assertNull(strategy.whoIsTheWinner(Item.Rock, Item.Rock));
	}

	@Test
	public void testScissorsScissors() {
		Assertions.assertNull(strategy.whoIsTheWinner(Item.Scissors, Item.Scissors));
	}
}
