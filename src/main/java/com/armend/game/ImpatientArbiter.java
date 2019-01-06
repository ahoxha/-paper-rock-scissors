package com.armend.game;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.armend.game.components.Item;
import com.armend.game.components.Player;
import com.armend.game.strategies.GameStrategy;

/**
 * Models an arbiter that doesn't wait forever for the players to play. It waits
 * a certain amount of time, and moves on. The player who failed to play on time
 * looses the round. If both players fail to play on time, the round ends on a
 * tie.
 * 
 * @author armend.hoxha
 *
 */
public class ImpatientArbiter extends Arbiter {

	private static final int defaultSecondsToWait = 4;
	private int secondsToWait;// time in seconds to wait for the player to play.

	/**
	 * Create an Arbiter with a given game strategy, two players and the max wait
	 * time in seconds 'secondsToWait' the arbiter must wait for players to play.
	 * 
	 * @param strategy      Game strategy to decide who's the winner.
	 * @param player1       First player.
	 * @param player2       Second player.
	 * @param secondsToWait Maximum wait time, in seconds, that the arbiter should
	 *                      wait for the players to play. This number should be
	 *                      greater than or equal to 1, otherwise it will be
	 *                      ignored, and the default value will be used.
	 */
	public ImpatientArbiter(GameStrategy strategy, Player player1, Player player2, int secondsToWait) {
		super(strategy, player1, player2);
		this.secondsToWait = secondsToWait < 1 ? defaultSecondsToWait : secondsToWait;
	}

	@Override
	public Player executeRound() {
		return executeAsync();
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

	public int getSecondsToWait() {
		return this.secondsToWait;
	}
}
