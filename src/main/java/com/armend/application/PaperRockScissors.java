package com.armend.application;

public class PaperRockScissors {
	public static void main(String[] args) {
		ComputerVsHumanConsoleGame game = ComputerVsHumanConsoleGame.initialize();
		game.start();
		game.end();
	}
}
