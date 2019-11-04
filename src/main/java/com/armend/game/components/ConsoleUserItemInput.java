package com.armend.game.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class ConsoleUserItemInput implements ItemInput {
	private BufferedReader reader;

	public ConsoleUserItemInput(Reader reader) {
		this.reader = new BufferedReader(reader);
	}

	@Override
	public Item get() {
		return readInputFromConsole();
	}

	private Item readInputFromConsole() {
		Item item;
		int tries = 3;
		do {
			System.out.println("Type S for Scissors, R for Rock, P for Paper:");
			String input;
			try {
				input = reader.readLine();
			} catch (IOException e) {
				input = "";
			}
			item = Item.of(input);
			if (item == null) {
				System.err.println("Wrong input: " + input);
			}
			tries--;
		} while (item == null && tries > 0);
		if (item == null) {
			System.out.print("Failed to get a valid input after 3 consequetive tries.");
		}
		return item;
	}
}
