package com.armend.game;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.armend.game.components.ComputerPlayer;
import com.armend.game.components.ConsoleUserItemInput;
import com.armend.game.components.HumanPlayer;
import com.armend.game.components.Player;
import com.armend.game.strategies.GameStrategy;
import com.armend.game.strategies.StandardStrategy;

public final class ComputerVsHumanConsoleGame {
	private Player player1;
	private Player player2;
	private Arbiter arbiter;
	private int rounds;
	private final static int MIN_ROUNDS = 3;
	private final static int MAX_ROUNDS = 100;
	private static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

	private ComputerVsHumanConsoleGame(Player player1, Player player2, int rounds, Arbiter arbiter) {
		this.player1 = player1;
		this.player2 = player2;
		this.rounds = rounds;
		this.arbiter = arbiter;
	}

	public static ComputerVsHumanConsoleGame initialize() {
		System.out.println("======= Rock Paper Scissors ======");
		System.out.println("How many rounds do you want to play? (Min: " + MIN_ROUNDS + ", Max: " + MAX_ROUNDS + "): ");
		int n = readRoundsFromUser();
		System.out.println("Type your name:");
		String name = scanner.nextLine();

		Player computerPlayer = new ComputerPlayer("Computer");
		Player humanPlayer = new HumanPlayer(name, new ConsoleUserItemInput(scanner));
		GameStrategy strategy = new StandardStrategy();
		Arbiter arbiter = new Arbiter(strategy, computerPlayer, humanPlayer);
		return new ComputerVsHumanConsoleGame(computerPlayer, humanPlayer, n, arbiter);
	}

	private static int readRoundsFromUser() {
		int tries = 3;
		do {
			String input = scanner.nextLine();
			try {
				int num = Integer.parseInt(input);
				validateRounds(num);
				return num;
			} catch (NumberFormatException e) {
				System.err.print("Invalid input: " + input + ". Please write a number.");
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
			tries--;
		} while (tries > 0);
		throw new RuntimeException("Failed to enter a valid input for the number of rounds.");
	}

	private static void validateRounds(int n) {
		if (n < MIN_ROUNDS || n > MAX_ROUNDS) {
			throw new IllegalArgumentException(
					"The 'rounds' argument must be between " + MIN_ROUNDS + " and " + MAX_ROUNDS);
		}
	}

	public void start() {
		System.out.println("Let's start playing between: " + player1.getName() + " and " + player2.getName()
				+ ", number of rounds: " + rounds);

		for (int i = 0; i < rounds; i++) {
			Player winner = arbiter.executeRound();
			if (winner != null) {
				System.out.print("Winner: " + winner.getName());
			} else {
				System.out.print("It's a tie.");
			}
			System.out.println(arbiter.getLastResult());
		}
	}

	public void end() {
		arbiter.printScores(System.out);
		scanner.close();
	}
}