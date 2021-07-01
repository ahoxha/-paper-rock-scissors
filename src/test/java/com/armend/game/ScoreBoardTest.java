package com.armend.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class ScoreBoardTest {
	@Test
	void testGetLastWithEmptyScoreBoard() {
		ScoreBoard board = new ScoreBoard("player1", "player2");
		assertEquals("", board.getLast());
	}

	@Test
	void testGetLastWithOneRecordScoreBoard() {
		ScoreBoard board = new ScoreBoard("player1", "player2");
		board.addRecords("Scissors", "Rock", "player2");
		assertEquals(" [player1: Scissors; player2: Rock]", board.getLast());
	}

	@Test
	void testGetLastWithMoreThanOneRecords() {
		ScoreBoard board = new ScoreBoard("player1", "player2");
		board.addRecords("Rock", "Rock", "It's a tie");
		board.addRecords("Scissors", "Paper", "player1");
		board.addRecords("Paper", "Paper", "It's a tie");
		assertEquals(" [player1: Paper; player2: Paper]", board.getLast());
	}

	@Test
	void testIncrementFirstPlayerScore() {
		ScoreBoard board = new ScoreBoard("Playe1", "Player2");
		board.incrementFirstPlayesScore();
		board.incrementFirstPlayesScore();
		assertEquals(2, board.getFirstPlayersTotalScore());
		// make sure when first incremented doesn't affect the others
		assertEquals(0, board.getSecondPlayersTotalScore());
		assertEquals(0, board.getTies());
	}

	@Test
	void testIncrementSecondPlayersScore() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		board.incrementSecondPlayersScore();
		assertEquals(1, board.getSecondPlayersTotalScore());
		// make sure when second incremented doesn't affect the others
		assertEquals(0, board.getFirstPlayersTotalScore());
		assertEquals(0, board.getTies());
	}

	@Test
	void testIncrementTies() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		board.incrementTies();
		board.incrementTies();
		board.incrementTies();
		assertEquals(3, board.getTies());
		// make sure when ties incremented doesn't affect the others
		assertEquals(0, board.getFirstPlayersTotalScore());
		assertEquals(0, board.getSecondPlayersTotalScore());
	}

	@Test
	void testIncrementAll() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		board.incrementSecondPlayersScore();
		board.incrementFirstPlayesScore();
		board.incrementFirstPlayesScore();
		board.incrementSecondPlayersScore();
		board.incrementTies();
		board.incrementSecondPlayersScore();
		assertEquals(1, board.getTies());
		assertEquals(2, board.getFirstPlayersTotalScore());
		assertEquals(3, board.getSecondPlayersTotalScore());
	}

	@Test
	void testPrintWithNullPrintStream() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		try {
			board.printTo(null);
			fail("Should not have reached this point, the 'printTo' method requires a non-null PrintStream.");
		} catch (NullPointerException e) {
			assertEquals("Please provide a non-null PrintStream.", e.getMessage());
		}
	}

	@Test
	void testNewScoreBoardWithNullFirstPlayer() {
		try {
			new ScoreBoard(null, "test");
			fail("Should not have reached this ppint, you must provide non-empty names for both players.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'firstPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	void testNewScoreBoardWithNullSecondPlayer() {
		try {
			new ScoreBoard("Player1", null);
		} catch (IllegalArgumentException e) {
			assertEquals("The 'secondPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	void testGetPlayers() {
		ScoreBoard board = new ScoreBoard("Computer", "John Doe");
		assertEquals("Computer", board.getFirstPlayer());
		assertEquals("John Doe", board.getSecondPlayer());
	}

	@Test
	void testNewScoreBoardWithEmptyFirstPlayer() {
		try {
			new ScoreBoard("", "test");
			fail("Should not have reached this ppint, you must provide non-empty names for both players.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'firstPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	void testNewScoreBoardWithEmptySecondPlayer() {
		try {
			new ScoreBoard("Test", "");
			fail("Should not have reached this ppint, you must provide non-empty names for both players.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'secondPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}
}
