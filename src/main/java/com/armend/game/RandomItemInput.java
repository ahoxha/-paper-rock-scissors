package com.armend.game;

import java.security.SecureRandom;

import com.armend.game.components.Item;

public class RandomItemInput implements ItemInput {

	private SecureRandom random;
	private Item[] components;
	
	public RandomItemInput() {
		random = new SecureRandom();
		components = Item.values();
	}

	@Override
	public Item get() {
		return components[random.nextInt(components.length)];
	}

}
