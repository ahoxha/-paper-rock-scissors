package com.armend.game.components;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class ConsoleUserItemInputTest {
	@Test
	void testGetItemUpperCase() {
		Reader reader = new StringReader("R\nS\nP");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	void testGetItemMakeSureItEndsOnMultibleWrongInputs() {
		Reader reader = new StringReader("A");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertNull(input.get());
	}

	@Test
	void testGetItemLowerCase() {
		Reader reader = new StringReader("r\ns\np");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	void testGetItemWithFullItemNames() {
		Reader reader = new StringReader("Rock\nScissors\nPaper");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	void testGetItemWithFullItemNamesUpperCase() {
		Reader reader = new StringReader("ROCK\nSCISSORS\nPAPER");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	void testGetItemWithFullItemNamesLowerCase() {
		Reader reader = new StringReader("rock\nscissors\npaper");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}
}
