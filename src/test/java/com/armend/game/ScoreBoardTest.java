package com.armend.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {
	@Test
	public void testGetLastWithEmptyScoreBoard() {
		ScoreBoard board = new ScoreBoard("player1", "player2");
		Assertions.assertEquals("", board.getLast());
	}

	@Test
	public void testGetLastWithOneRecordScoreBoard() {
		ScoreBoard board = new ScoreBoard("player1", "player2");
		board.addRecords("Scissors", "Rock", "player2");
		Assertions.assertEquals(" [player1: Scissors; player2: Rock]", board.getLast());
	}

	@Test
	public void testGetLastWithMoreThanOneRecords() {
		ScoreBoard board = new ScoreBoard("player1", "player2");
		board.addRecords("Rock", "Rock", "It's a tie");
		board.addRecords("Scissors", "Paper", "player1");
		board.addRecords("Paper", "Paper", "It's a tie");
		Assertions.assertEquals(" [player1: Paper; player2: Paper]", board.getLast());
	}

	@Test
	public void testIncrementFirstPlayerScore() {
		ScoreBoard board = new ScoreBoard("Playe1", "Player2");
		board.incrementFirstPlayesScore();
		board.incrementFirstPlayesScore();
		Assertions.assertEquals(2, board.getFirstPlayersTotalScore());
		// make sure when first incremented doesn't affect the others
		Assertions.assertEquals(0, board.getSecondPlayersTotalScore());
		Assertions.assertEquals(0, board.getTies());
	}

	@Test
	public void testIncrementSecondPlayersScore() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		board.incrementSecondPlayersScore();
		Assertions.assertEquals(1, board.getSecondPlayersTotalScore());
		// make sure when second incremented doesn't affect the others
		Assertions.assertEquals(0, board.getFirstPlayersTotalScore());
		Assertions.assertEquals(0, board.getTies());
	}

	@Test
	public void testIncrementTies() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		board.incrementTies();
		board.incrementTies();
		board.incrementTies();
		Assertions.assertEquals(3, board.getTies());
		// make sure when ties incremented doesn't affect the others
		Assertions.assertEquals(0, board.getFirstPlayersTotalScore());
		Assertions.assertEquals(0, board.getSecondPlayersTotalScore());
	}

	@Test
	public void testIncrementAll() {
		ScoreBoard board = new ScoreBoard("Player1", "Player2");
		board.incrementSecondPlayersScore();
		board.incrementFirstPlayesScore();
		board.incrementFirstPlayesScore();
		board.incrementSecondPlayersScore();
		board.incrementTies();
		board.incrementSecondPlayersScore();
		Assertions.assertEquals(1, board.getTies());
		Assertions.assertEquals(2, board.getFirstPlayersTotalScore());
		Assertions.assertEquals(3, board.getSecondPlayersTotalScore());
	}
}
