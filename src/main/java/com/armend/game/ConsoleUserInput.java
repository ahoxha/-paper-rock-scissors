package com.armend.game;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.armend.game.components.Item;

public class ConsoleUserInput implements UserInput {

	private Scanner scanner;
	private final int MAX_TRIES = 5;

	public ConsoleUserInput() {
		scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
	}

	@Override
	public Item get() {
		int tries = 0;
		Item item = null;
		do {
			System.out.println("Type S for Scissors, R for Rock, P for Paper:");
			String input = scanner.nextLine().toUpperCase();
			if (input.equals("S")) {
				item = Item.Scissors;
			} else if (input.equals("P")) {
				item = Item.Paper;
			} else if (input.equals("R")) {
				item = Item.Rock;
			}
			if (item == null) {
				System.err.println("Wrong value: " + input);
			}
			tries++;
		} while (item == null && tries < MAX_TRIES);
		return item;
	}

	public void close() {
		scanner.close();
	}
}
