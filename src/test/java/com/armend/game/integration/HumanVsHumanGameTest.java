package com.armend.game.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import com.armend.game.Arbiter;
import com.armend.game.components.ConsoleUserItemInput;
import com.armend.game.components.HumanPlayer;
import com.armend.game.components.Item;
import com.armend.game.components.ItemInput;
import com.armend.game.components.Player;
import com.armend.game.rules.StandardDecisionRules;

class HumanVsHumanGameTest {

    @Test
    void testPlayHumanVsHuman() {
        ItemInput player1Input = new ConsoleUserItemInput(new StringReader("S\nP\nR\nR"));
        Player player1 = new HumanPlayer("Player1", player1Input);

        ItemInput player2Input = new ConsoleUserItemInput(new StringReader("P\nS\nS\nR"));
        Player player2 = new HumanPlayer("Player2", player2Input);

        Arbiter arbiter = new Arbiter(new StandardDecisionRules(), player1, player2);

        assertEquals(player1, arbiter.executeRound());
        assertEquals(String.format(" [Player1: %s; Player2: %s]", Item.SCISSORS, Item.PAPER), arbiter.getLastResult());
        assertEquals(player2, arbiter.executeRound());
        assertEquals(player1, arbiter.executeRound());
        assertNull(arbiter.executeRound());
        assertEquals(2, arbiter.getFirstPlayerTotalScore());
        assertEquals(1, arbiter.getSecondPlayerTotalScore());
        assertEquals(1, arbiter.getTies());
        assertEquals(String.format(" [Player1: %s; Player2: %s]", Item.ROCK, Item.ROCK), arbiter.getLastResult());
    }

    @Test
    void testPlayHumanVsHumanWithWrongFirstPlayerInput() {
        ItemInput player1Input = new ConsoleUserItemInput(new StringReader("A"));
        Player player1 = new HumanPlayer("Player1", player1Input);

        ItemInput player2Input = new ConsoleUserItemInput(new StringReader("R"));
        Player player2 = new HumanPlayer("Player2", player2Input);

        Arbiter arbiter = new Arbiter(new StandardDecisionRules(), player1, player2);
        try {
            assertEquals(player1, arbiter.executeRound());
        } catch (IllegalArgumentException e) {
            assertEquals("firstItem should not be null", e.getMessage());
        }
        assertEmptyScoreBoard(arbiter);
    }

    @Test
    void testPlayHumanVsHumanWithWrongSecondPlayerInput() {
        ItemInput player1Input = new ConsoleUserItemInput(new StringReader("S"));
        Player player1 = new HumanPlayer("Player1", player1Input);

        ItemInput player2Input = new ConsoleUserItemInput(new StringReader("A"));
        Player player2 = new HumanPlayer("Player2", player2Input);

        Arbiter arbiter = new Arbiter(new StandardDecisionRules(), player1, player2);
        try {
            assertEquals(player1, arbiter.executeRound());
        } catch (IllegalArgumentException e) {
            assertEquals("secondItem should not be null", e.getMessage());
        }
        assertEmptyScoreBoard(arbiter);
    }

    private void assertEmptyScoreBoard(Arbiter arbiter) {
        assertEquals(0, arbiter.getFirstPlayerTotalScore());
        assertEquals(0, arbiter.getSecondPlayerTotalScore());
        assertEquals(0, arbiter.getTies());
    }
}
