package com.armend.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

class ScoreBoardTest {
	@Test
	void when_empty_then_getLast_returns_empty_string() {
		ScoreBoard board = new ScoreBoard("player1", "player2");

		assertEquals("", board.getLast());
	}

	@Test
	void when_one_record_then_getLast_returns_that_record() {
		ScoreBoard board = new ScoreBoard("player1", "player2");

		board.addRecords("Scissors", "Rock", "player2");

		assertEquals(" [player1: Scissors; player2: Rock]", board.getLast());
	}

	@Test
	void when_many_records_then_getLast_returns_the_last_one() {
		ScoreBoard board = new ScoreBoard("player1", "player2");

		board.addRecords("Rock", "Rock", "It's a tie");
		board.addRecords("Scissors", "Paper", "player1");
		board.addRecords("Paper", "Paper", "It's a tie");

		assertEquals(" [player1: Paper; player2: Paper]", board.getLast());
	}

	@Test
	void when_first_player_score_is_incremented_then_others_are_not_affected() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");

		board.incrementFirstPlayesScore();
		board.incrementFirstPlayesScore();

		assertEquals(2, board.getFirstPlayersTotalScore());
		assertEquals(0, board.getSecondPlayersTotalScore());
		assertEquals(0, board.getTies());
	}

	@Test
	void when_second_play_score_is_incremented_then_others_are_not_affected() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");

		board.incrementSecondPlayersScore();

		assertEquals(1, board.getSecondPlayersTotalScore());
		assertEquals(0, board.getFirstPlayersTotalScore());
		assertEquals(0, board.getTies());
	}

	@Test
	void when_number_of_ties_is_incremented_then_other_scored_are_not_affected() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");

		board.incrementTies();
		board.incrementTies();
		board.incrementTies();

		assertEquals(3, board.getTies());
		assertEquals(0, board.getFirstPlayersTotalScore());
		assertEquals(0, board.getSecondPlayersTotalScore());
	}

	@Test
	void when_all_scores_are_incremented_then_expect_new_values() {
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
	void when_null_PrintStream_provided_then_expect_exception() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		try {
			board.printTo(null);
			fail("Should not have reached this point, the 'printTo' method requires a non-null PrintStream.");
		} catch (NullPointerException e) {
			assertEquals("Please provide a non-null PrintStream.", e.getMessage());
		}
	}

	@Test
	void when_null_first_player_provided_then_expect_exception() {
		try {
			new ScoreBoard(null, "test");
			fail("Should not have reached this point, you must provide non-empty names for both players.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'firstPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	void when_null_second_player_provided_then_expect_exception() {
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
	void when_empty_first_player_provided_then_expect_exception() {
		try {
			new ScoreBoard("", "test");
			fail("Should not have reached this ppint, you must provide non-empty names for both players.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'firstPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}

	@Test
	void when_empty_second_player_provided_then_expect_exception() {
		try {
			new ScoreBoard("Test", "");
			fail("Should not have reached this ppint, you must provide non-empty names for both players.");
		} catch (IllegalArgumentException e) {
			assertEquals("The 'secondPlayer' argument must be non-null and non-empty.", e.getMessage());
		}
	}
}
