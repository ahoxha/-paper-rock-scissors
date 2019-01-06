package com.armend.application;

import com.armend.game.Arbiter;
import com.armend.game.ImpatientArbiter;
import com.armend.game.components.ComputerPlayer;
import com.armend.game.components.ConsoleUserInput;
import com.armend.game.components.HumanPlayer;
import com.armend.game.components.Player;
import com.armend.game.strategies.StandardStrategy;

public class PaperRockScissors {
	public static void main(String[] args) {
		int n = 5;
		Player player1 = new ComputerPlayer("Computer");
		ConsoleUserInput input = new ConsoleUserInput();
		Player player2 = new HumanPlayer("Armend", input);
		System.out.println("======= Rock Paper Scissors ======");
		System.out.println("Let's start playing between: " + player1.getName() + " and " + player2.getName()
				+ ", number of rounds: " + n);
		Arbiter arbiter = new ImpatientArbiter(new StandardStrategy(), player1, player2, 2);
					     //new Arbiter(new StandardStrategy(), player1, player2);
		for (int i = 0; i < n; i++) {
			Player winner = arbiter.executeRound();
			if (winner != null) {
				System.out.print("Winner: " + winner.getName());
			} else {
				System.out.print("It's a tie.");
			}
			System.out.println(arbiter.getLastResult());
		}
		input.close();
		arbiter.printScores(System.out);
	}
}
