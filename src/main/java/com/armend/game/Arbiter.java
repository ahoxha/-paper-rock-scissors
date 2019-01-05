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
	private final int SECONDS_TO_WAIT_FOR_PLAYER = 4;

	public Arbiter(GameStrategy strategy, Player player1, Player player2) {
		Objects.requireNonNull(strategy, "NULL value for 'strategy' is not allowed");
		Objects.requireNonNull(player1, "NULL value for 'player1' is not allowed");
		Objects.requireNonNull(player2, "NULL value for 'player2' is not allwed");
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

	public void printScores(PrintStream stream) {
		scoreBoard.printTo(stream);
	}

	/**
	 * Ask the players to make the move and decide who is the winner. It waits a
	 * certain time for a player, if the player fails to play on time looses the
	 * round.
	 * 
	 * @return The winning player, or null if there is a tie between them.
	 */
	public Player executeRound() {
		Item player1Item = getFirstPlayersItem();
		Item player2Item = getSecondPlayersItem();
		ForkJoinPool.commonPool().awaitQuiescence(SECONDS_TO_WAIT_FOR_PLAYER, TimeUnit.SECONDS);

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

		// if both players have made the move, then decide who's the winner
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

	private Item getFirstPlayersItem() {
		Item player1Item;
		CompletableFuture<Item> future1 = CompletableFuture.supplyAsync(() -> {
			return player1.play();
		});
		try {
			player1Item = future1.get(SECONDS_TO_WAIT_FOR_PLAYER, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			player1Item = null;
		}
		return player1Item;
	}

	private Item getSecondPlayersItem() {
		Item player2Item;
		CompletableFuture<Item> future2 = CompletableFuture.supplyAsync(() -> {
			return player2.play();
		});

		try {
			player2Item = future2.get(SECONDS_TO_WAIT_FOR_PLAYER, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			player2Item = null;
		}
		return player2Item;
	}

	public String getLastResult() {
		return scoreBoard.getLast();
	}
}
