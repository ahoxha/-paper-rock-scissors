package com.armend.game;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.armend.game.components.Item;

public class ConsoleUserInput implements UserInput {

	private Scanner scanner;

	public ConsoleUserInput() {
		scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
	}

	@Override
	public Item get() {
		Item item = null;
		do {
			System.out.println("Type S for Scissors, R for Rock, P for Paper:");
			String input = scanner.nextLine();
			item = Item.of(input);
			if (item == null) {
				System.err.println("Wrong input: " + input);
			}
		} while (item == null);
		return item;
	}

	public void close() {
		scanner.close();
	}
}
