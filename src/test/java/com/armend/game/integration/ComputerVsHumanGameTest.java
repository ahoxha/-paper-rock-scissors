package com.armend.game.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import com.armend.game.Arbiter;
import com.armend.game.ImpatientArbiter;
import com.armend.game.components.ComputerPlayer;
import com.armend.game.components.ConsoleUserItemInput;
import com.armend.game.components.HumanPlayer;
import com.armend.game.components.ItemInput;
import com.armend.game.components.Player;
import com.armend.game.rules.StandardDecisionRules;

class ComputerVsHumanGameTest {
	@Test
	void testPlayComputerVsHuman() {
		ItemInput player1Input = new ConsoleUserItemInput(new StringReader("S\nP\nR\nR"));
		Player human = new HumanPlayer("Player1", player1Input);

		ItemInput computerInput = new ConsoleUserItemInput(new StringReader("P\nS\nS\nR"));
		Player computer = new ComputerPlayer("Computer", computerInput);

		Arbiter arbiter = new ImpatientArbiter(new StandardDecisionRules(), human, computer, 2);

		assertEquals(human, arbiter.executeRound());
		assertEquals(computer, arbiter.executeRound());
		assertEquals(human, arbiter.executeRound());
		assertNull(arbiter.executeRound());
		assertEquals(2, arbiter.getFirstPlayerTotalScore());
		assertEquals(1, arbiter.getSecondPlayerTotalScore());
		assertEquals(1, arbiter.getTies());
	}

	@Test
	void testPlayComputerWithRandomItemInputVsHuman() {
		ItemInput player1Input = new ConsoleUserItemInput(new StringReader("S\nP\nR"));
		Player human = new HumanPlayer("Player1", player1Input);

		Player computer = new ComputerPlayer("Computer");

		Arbiter arbiter = new ImpatientArbiter(new StandardDecisionRules(), human, computer, 2);
		// we don't know who is the winner since the computer player chooses randomly
		arbiter.executeRound();
		arbiter.executeRound();
		arbiter.executeRound();
		assertEquals(3, arbiter.getFirstPlayerTotalScore() + arbiter.getSecondPlayerTotalScore() + arbiter.getTies());
	}

	@Test
	void testPlayComputerWithRandomItemInputVsHumanWithWrongInput() {
		ItemInput player1Input = new ConsoleUserItemInput(new StringReader("A\nA\nA"));
		Player human = new HumanPlayer("Player1", player1Input);

		Player computer = new ComputerPlayer("Computer");

		Arbiter arbiter = new ImpatientArbiter(new StandardDecisionRules(), human, computer, 2);
		assertEquals(computer, arbiter.executeRound());
		assertEquals(1, arbiter.getSecondPlayerTotalScore());
		assertEquals(0, arbiter.getFirstPlayerTotalScore());
		assertEquals(0, arbiter.getTies());
	}
}
