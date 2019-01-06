package com.armend.game.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.armend.game.Arbiter;
import com.armend.game.ImpatientArbiter;
import com.armend.game.components.ConsoleUserItemInput;
import com.armend.game.components.HumanPlayer;
import com.armend.game.components.Item;
import com.armend.game.components.ItemInput;
import com.armend.game.components.Player;
import com.armend.game.strategies.StandardStrategy;

public class HumanVsHumanTimedGameTest {
	@Test
	public void testPlayHumanVsHuman() {
		ItemInput player1Input = new ConsoleUserItemInput(new StringReader("S\nP\nR\nR"));
		Player player1 = new HumanPlayer("Player1", player1Input);

		ItemInput player2Input = new ConsoleUserItemInput(new StringReader("P\nS\nS\nR"));
		Player player2 = new HumanPlayer("Player2", player2Input);

		Arbiter arbiter = new ImpatientArbiter(new StandardStrategy(), player1, player2, 2);

		assertEquals(player1, arbiter.executeRound());
		assertEquals(player2, arbiter.executeRound());
		assertEquals(player1, arbiter.executeRound());
		assertNull(arbiter.executeRound());
		assertEquals(2, arbiter.getFirstPlayerTotalScore());
		assertEquals(1, arbiter.getSecondPlayerTotalScore());
		assertEquals(1, arbiter.getTies());
	}

	@Test
	public void testPlayHumanVsHumanWithSecondFirstTimeout() throws UnsupportedEncodingException {

		ItemInput player1Input = new ConsoleUserItemInput(
				new InputStreamReader(System.in, StandardCharsets.UTF_8.name()));
		Player player1 = new HumanPlayer("Player1", player1Input);

		ItemInput player2Input = new ConsoleUserItemInput(new StringReader("S\nP\nR\nR"));
		Player player2 = new HumanPlayer("Player1", player2Input);

		Arbiter arbiter = new ImpatientArbiter(new StandardStrategy(), player1, player2, 1);
		assertEquals(player2, arbiter.executeRound());
		assertNull(player1.getPreviousItem());
		assertEquals(Item.Scissors, player2.getPreviousItem());
		assertEquals(0, arbiter.getFirstPlayerTotalScore());
		assertEquals(1, arbiter.getSecondPlayerTotalScore());
		assertEquals(0, arbiter.getTies());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		arbiter.printScores(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
		assertTrue(new String(out.toByteArray(), StandardCharsets.UTF_8).contains("Timed out"));
	}

	@Test
	public void testPlayHumanVsHumanWithSecondPlayerTimeout() throws UnsupportedEncodingException {
		ItemInput player1Input = new ConsoleUserItemInput(new StringReader("S\nP\nR\nR"));
		Player player1 = new HumanPlayer("Player1", player1Input);

		ItemInput player2Input = new ConsoleUserItemInput(
				new InputStreamReader(System.in, StandardCharsets.UTF_8.name()));
		Player player2 = new HumanPlayer("Player2", player2Input);

		Arbiter arbiter = new ImpatientArbiter(new StandardStrategy(), player1, player2, 1);
		assertEquals(player1, arbiter.executeRound());
		assertNull(player2.getPreviousItem());
		assertEquals(Item.Scissors, player1.getPreviousItem());
		assertEquals(1, arbiter.getFirstPlayerTotalScore());
		assertEquals(0, arbiter.getSecondPlayerTotalScore());
		assertEquals(0, arbiter.getTies());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		arbiter.printScores(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
		assertTrue(new String(out.toByteArray(), StandardCharsets.UTF_8).contains("Timed out"));
	}

	@Test
	public void testPlayHumanVsHumanWithBothPlayersTimeout() throws UnsupportedEncodingException {
		ItemInput player1Input = new ConsoleUserItemInput(
				new InputStreamReader(System.in, StandardCharsets.UTF_8.name()));
		Player player1 = new HumanPlayer("Player1", player1Input);

		ItemInput player2Input = new ConsoleUserItemInput(
				new InputStreamReader(System.in, StandardCharsets.UTF_8.name()));
		Player player2 = new HumanPlayer("Player2", player2Input);

		Arbiter arbiter = new ImpatientArbiter(new StandardStrategy(), player1, player2, 1);
		assertNull(arbiter.executeRound());
		assertNull(player2.getPreviousItem());
		assertNull(player1.getPreviousItem());
		assertEquals(0, arbiter.getFirstPlayerTotalScore());
		assertEquals(0, arbiter.getSecondPlayerTotalScore());
		assertEquals(1, arbiter.getTies());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		arbiter.printScores(new PrintStream(out, true, StandardCharsets.UTF_8.name()));
		assertTrue(new String(out.toByteArray(), StandardCharsets.UTF_8).contains("Timed out"));
	}

	@Test
	public void testPlayHumanVsHumanWithWrongInput() {
		ItemInput player1Input = new ConsoleUserItemInput(new StringReader("A\nB\nC"));
		Player player1 = new HumanPlayer("Player1", player1Input);

		ItemInput player2Input = new ConsoleUserItemInput(new StringReader("P"));
		Player player2 = new HumanPlayer("Player2", player2Input);

		Arbiter arbiter = new ImpatientArbiter(new StandardStrategy(), player1, player2, 2);

		assertEquals(player2, arbiter.executeRound());
		assertEquals(0, arbiter.getFirstPlayerTotalScore());
		assertEquals(1, arbiter.getSecondPlayerTotalScore());
		assertEquals(0, arbiter.getTies());
	}
}
