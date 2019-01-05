package com.armend.game;

import java.io.PrintStream;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.armend.game.components.Item;
import com.armend.game.components.Player;
import com.armend.game.strategies.GameStrategy;

public class Arbiter {
	private Player player1;
	private Player player2;
	private GameStrategy strategy;
	private ScoreBoard scoreBoard;
	private static final int defaultSecondsToWait = 4;
	private int secondsToWait;// time in seconds to wait for the user if 'async' is true
	private boolean async; // if true, it will time out if the players don't respond on time, otherwise it
							// will wait indefinitely.

	/**
	 * Create an arbiter with a given game strategy and two players. The 'async'
	 * parameter is set to true, and the default wait time is used, which is 4
	 * seconds. The arbiter will wait for the players to play maximum 4 seconds.
	 * 
	 * @param strategy Game strategy to decide who's he winner.
	 * @param player1  First player.
	 * @param player2  Second player.
	 */
	public Arbiter(GameStrategy strategy, Player player1, Player player2) {
		this(strategy, player1, player2, true, defaultSecondsToWait);
	}

	/**
	 * Create an Arbiter with a given game strategy, two players and if the arbiter
	 * should wait indefinitely for the players to play. If he arbiter should not
	 * wait indefinitely, then specify the max wait time in seconds 'secondsToWait'.
	 * 
	 * @param strategy      Game strategy to decide who's the winner.
	 * @param player1       First player.
	 * @param player2       Second player.
	 * @param async         Set this to true if the arbiter should not wait forever
	 *                      for the players to play, and set the wait time in
	 *                      seconds. In case you want the arbiter to wait until the
	 *                      players make their move, then set this to false.
	 * @param secondsToWait Maximum wait time, in seconds, that the arbiter should
	 *                      wait for the players to play. This number should be
	 *                      greater than or equal to 1, otherwise it will be
	 *                      ignored. If 'async' is false, this number is ignored.
	 */
	public Arbiter(GameStrategy strategy, Player player1, Player player2, boolean async, int secondsToWait) {
		Objects.requireNonNull(strategy, "NULL value for 'strategy' is not allowed");
		Objects.requireNonNull(player1, "NULL value for 'player1' is not allowed");
		Objects.requireNonNull(player2, "NULL value for 'player2' is not allwed");
		this.strategy = strategy;
		this.player1 = player1;
		this.player2 = player2;
		scoreBoard = new ScoreBoard(player1.getName(), player2.getName());
		this.async = async;
		this.secondsToWait = secondsToWait < 1 ? defaultSecondsToWait : secondsToWait;
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

	public void printScores(PrintStream stream) {
		scoreBoard.printTo(stream);
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
		if (async) {
			return executeAsync();
		}
		return executeSync();
	}

	private Player executeSync() {
		return getWinner(player1.play(), player2.play());
	}

	private Player executeAsync() {
		Item player1Item = getFirstPlayersItem();
		Item player2Item = getSecondPlayersItem();
		ForkJoinPool.commonPool().awaitQuiescence(secondsToWait, TimeUnit.SECONDS);

		if (player1Item == null && player2Item == null) {
			scoreBoard.addRecords("Timed out", "Timed out", "It's a tie");
			scoreBoard.incrementTies();
			return null;
		}
		if (player1Item == null) {
			scoreBoard.addRecords("Timed out", player2Item.name(), player2.getName());
			scoreBoard.incrementSecondPlayersScore();
			return player2;
		}
		if (player2Item == null) {
			scoreBoard.addRecords(player1Item.name(), "Timed out", player1.getName());
			scoreBoard.incrementFirstPlayesScore();
			return player1;
		}

		// if both players have made the move, then decide who's the winner based on the
		// strategy
		return getWinner(player1Item, player2Item);
	}

	/**
	 * Given the selected items from the two players, return the winning player
	 * based on the game's strategy.
	 * 
	 * @param player1Item Item selected by the first player.
	 * @param player2Item Item selected by the second player.
	 * @return The winning player or null if there's a tie.
	 */
	private Player getWinner(Item player1Item, Item player2Item) {
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

	/**
	 * Retrieve the first player's selected item asynchronously.
	 * 
	 * @return The selected item, or null if timed out.
	 */
	private Item getFirstPlayersItem() {
		CompletableFuture<Item> future1 = CompletableFuture.supplyAsync(() -> {
			return player1.play();
		});
		try {
			return future1.get(secondsToWait, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			return null;
		}
	}

	/**
	 * Retrieve the second player's selected item asynchronously.
	 * 
	 * @return The selected item, or null if timed out.
	 */
	private Item getSecondPlayersItem() {
		CompletableFuture<Item> future2 = CompletableFuture.supplyAsync(() -> {
			return player2.play();
		});

		try {
			return future2.get(secondsToWait, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			return null;
		}
	}

	public String getLastResult() {
		return scoreBoard.getLast();
	}
}
