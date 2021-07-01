package com.armend.game;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.armend.game.components.Item;
import com.armend.game.components.Player;
import com.armend.game.rules.DecisionRules;

/**
 * Models an arbiter that doesn't wait forever for the players to play. It waits
 * a certain amount of time, and moves on. The player who fails to play on time
 * loses the round. If both players fail to play on time, the round ends on a
 * tie.
 *
 * @author armend.hoxha
 */
public class ImpatientArbiter extends Arbiter {

    private static final int DEFAULT_SECONDS_TO_WAIT = 4;
    private static final String TIMED_OUT = "Timed out";
    private final int secondsToWait;// time in seconds to wait for the player to play.

    /**
     * Create an Arbiter with the given decision rules, two players and the max wait
     * time in seconds 'secondsToWait' the arbiter must wait for players to play.
     *
     * @param decisionRules Game rules to decide who's the winner.
     * @param player1       First player.
     * @param player2       Second player.
     * @param secondsToWait Maximum wait time, in seconds, that the arbiter should
     *                      wait for the players to play. This number should be
     *                      greater than or equal to 1, otherwise it will be
     *                      ignored, and the default value will be used.
     */
    public ImpatientArbiter(DecisionRules decisionRules, Player player1, Player player2, int secondsToWait) {
        super(decisionRules, player1, player2);
        this.secondsToWait = secondsToWait < 1 ? DEFAULT_SECONDS_TO_WAIT : secondsToWait;
    }

    @Override
    public Player executeRound() {
        return executeAsync();
    }

    private Player executeAsync() {
        var player1Item = getFirstPlayersItem();
        var player2Item = getSecondPlayersItem();
        ForkJoinPool.commonPool().awaitQuiescence(secondsToWait, TimeUnit.SECONDS);

        if (noPlayerPlayed(player1Item, player2Item)) {
            scoreBoard.addRecords(TIMED_OUT, TIMED_OUT, "It's a tie");
            scoreBoard.incrementTies();
            return null;
        }
        if (didPlayerPlay(player1Item)) {
            scoreBoard.addRecords(TIMED_OUT, player2Item.name(), player2.getName());
            scoreBoard.incrementSecondPlayersScore();
            return player2;
        }
        if (didPlayerPlay(player2Item)) {
            scoreBoard.addRecords(player1Item.name(), TIMED_OUT, player1.getName());
            scoreBoard.incrementFirstPlayesScore();
            return player1;
        }

        // if both players have made the move, then decide who's the winner based on the
        // rules
        return getWinner(player1Item, player2Item);
    }

    private boolean didPlayerPlay(Item player1Item) {
        return player1Item == null;
    }

    private boolean noPlayerPlayed(Item player1Item, Item player2Item) {
        return didPlayerPlay(player1Item) && didPlayerPlay(player2Item);
    }

    /**
     * Retrieve the first player's selected item asynchronously.
     *
     * @return The selected item, or null if timed out.
     */
    private Item getFirstPlayersItem() {
        CompletableFuture<Item> future1 = CompletableFuture.supplyAsync(() -> player1.play());
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
        CompletableFuture<Item> future2 = CompletableFuture.supplyAsync(() -> player2.play());

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
