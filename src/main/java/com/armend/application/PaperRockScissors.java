package com.armend.application;

import com.armend.game.ComputerVsHumanConsoleGame;

public class PaperRockScissors {
	public static void main(String[] args) {
		ComputerVsHumanConsoleGame game = ComputerVsHumanConsoleGame.initialize();
		game.start();
		game.end();
	}
}
