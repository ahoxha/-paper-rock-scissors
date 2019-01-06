package com.armend.game.components;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class ConsoleUserItemInputTest {
	@Test
	public void testGetItemUpperCase() {
		Reader reader = new StringReader("R\nS\nP");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	public void testGetItemMakeSureItEndsOnMultibleWrongInputs() {
		Reader reader = new StringReader("A");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertNull(input.get());
	}

	@Test
	public void testGetItemLowerCase() {
		Reader reader = new StringReader("r\ns\np");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	public void testGetItemWithFullItemNames() {
		Reader reader = new StringReader("Rock\nScissors\nPaper");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	public void testGetItemWithFullItemNamesUpperCase() {
		Reader reader = new StringReader("ROCK\nSCISSORS\nPAPER");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}

	@Test
	public void testGetItemWithFullItemNamesLowerCase() {
		Reader reader = new StringReader("rock\nscissors\npaper");
		ConsoleUserItemInput input = new ConsoleUserItemInput(reader);
		assertEquals(Item.Rock, input.get());
		assertEquals(Item.Scissors, input.get());
		assertEquals(Item.Paper, input.get());
	}
}
