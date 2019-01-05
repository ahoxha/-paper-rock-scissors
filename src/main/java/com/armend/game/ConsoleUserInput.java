package com.armend.game;

import java.util.Scanner;

import com.armend.game.components.Item;

public class ConsoleUserInput implements UserInput {

	private Scanner scanner;

	public ConsoleUserInput() {
		scanner = new Scanner(System.in);
	}

	@Override
	public Item get() {
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
		} while (item == null);
		return item;
	}

	public void close() {
		scanner.close();
	}

}
