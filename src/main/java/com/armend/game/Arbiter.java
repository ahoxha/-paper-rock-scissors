package com.armend.game;

import java.io.PrintStream;
import java.util.Objects;

import com.armend.game.components.Item;
import com.armend.game.components.Player;
import com.armend.game.strategies.GameStrategy;

public class Arbiter {
	protected Player player1;
	protected Player player2;
	protected GameStrategy strategy;
	protected ScoreBoard scoreBoard;

	/**
	 * Create an arbiter with a given game strategy and two players. This arbiter
	 * will wait indefinitely for the players to play.
	 * 
	 * @param strategy Game strategy to decide who's he winner.
	 * @param player1  First player.
	 * @param player2  Second player.
	 */
	public Arbiter(GameStrategy strategy, Player player1, Player player2) {
		Objects.requireNonNull(strategy, "NULL value for 'strategy' is not allowed");
		Objects.requireNonNull(player1, "NULL value for 'player1' is not allowed");
		Objects.requireNonNull(player2, "NULL value for 'player2' is not allowed");
		this.strategy = strategy;
		this.player1 = player1;
		this.player2 = player2;
		scoreBoard = new ScoreBoard(player1.getName(), player2.getName());
	}

	/**
	 * Set the new strategy if 'strategy' is not null. If null argument provided,
	 * then it's ignored.
	 * 
	 * @param strategy The new game strategy to be used by the arbiter.
	 */
	public void setStrategy(GameStrategy strategy) {
		if (strategy != null) {
			this.strategy = strategy;
		}
	}

	public GameStrategy getStrategy() {
		return this.strategy;
	}

	public void printScores(PrintStream stream) {
		scoreBoard.printTo(stream);
	}

	public int getFirstPlayerTotalScore() {
		return scoreBoard.getFirstPlayersTotalScore();
	}

	public int getSecondPlayerTotalScore() {
		return scoreBoard.getSecondPlayersTotalScore();
	}

	public int getTies() {
		return scoreBoard.getTies();
	}

	/**
	 * Ask the players to make the move and decide who is the winner. If 'async' is
	 * true, it waits a certain time for a player, if the player fails to play on
	 * time looses the round. If 'async' is false, it waits for the player
	 * indefinitely.
	 * 
	 * @return The winning player, or null if there is a tie between them.
	 */
	public Player executeRound() {
		return getWinner(player1.play(), player2.play());
	}

	/**
	 * Given the selected items from the two players, return the winning player
	 * based on the game's strategy.
	 * 
	 * @param player1Item Item selected by the first player.
	 * @param player2Item Item selected by the second player.
	 * @return The winning player or null if there's a tie.
	 */
	protected Player getWinner(Item player1Item, Item player2Item) {
		Item result = strategy.whoIsTheWinner(player1Item, player2Item);
		if (result == null) {
			scoreBoard.addRecords(player1Item.name(), player2Item.name(), "It's a tie");
			scoreBoard.incrementTies();
			return null;
		}
		if (result == player1Item) {
			scoreBoard.addRecords(player1Item.name(), player2Item.name(), player1.getName());
			scoreBoard.incrementFirstPlayesScore();
			return player1;
		}
		scoreBoard.addRecords(player1Item.name(), player2Item.name(), player2.getName());
		scoreBoard.incrementSecondPlayersScore();
		return player2;
	}

	public String getLastResult() {
		return scoreBoard.getLast();
	}
}
