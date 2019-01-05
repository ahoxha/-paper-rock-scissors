package com.armend.game;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

	private List<Record> records = new ArrayList<>();
	private String firstPlayer;
	private String secondPlayer;
	private int firstPlayersTotalScore;
	private int secondPlayersTotalScore;
	private int ties;

	public ScoreBoard(String firstPlayer, String secondPlayer) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
	}

	public void printTo(PrintStream stream) {
		int columnWidth = -35;
		StringBuilder builder = new StringBuilder();
		builder.append("+");
		for (int i = 0; i < 3 * Math.abs(columnWidth) + 8; i++) {
			builder.append("-");
		}
		builder.append("+");
		stream.println(builder.toString());
		stream.println("\n================ Score Board =========================");
		stream.println(builder.toString());
		String format = "| %" + columnWidth + "s | %" + columnWidth + "s | %" + columnWidth + "s |";
		stream.println(String.format(format, firstPlayer, secondPlayer, "Winner"));
		stream.println(builder.toString());
		for (Record record : records) {
			stream.println(String.format(format, record.getFirstPlayersChoice(), record.getSecondPlayersChoice(),
					record.getWinner()));
		}
		stream.print(builder.toString());
		stream.println("\nTotals:");
		stream.println(builder.toString());
		stream.println(String.format(format, firstPlayer, secondPlayer, "Ties"));
		stream.println(builder.toString());
		stream.println(String.format(format, firstPlayersTotalScore, secondPlayersTotalScore, ties));
		stream.println(builder.toString());
	}

	public void addRecords(String firstPlayersChoise, String secondPlayersChoice, String winner) {
		records.add(new Record(firstPlayersChoise, secondPlayersChoice, winner));
	}

	public void incrementFirstPlayesScore() {
		firstPlayersTotalScore++;
	}

	public void incrementSecondPlayersScore() {
		secondPlayersTotalScore++;
	}

	public void incementTies() {
		ties++;
	}

	private static class Record {
		private String firstPlayersChoice;
		private String secondPlayersChoice;
		private String winner;

		Record(String first, String second, String winner) {
			this.firstPlayersChoice = first;
			this.secondPlayersChoice = second;
			this.winner = winner;
		}

		public String getFirstPlayersChoice() {
			return firstPlayersChoice;
		}

		public String getSecondPlayersChoice() {
			return secondPlayersChoice;
		}

		public String getWinner() {
			return winner;
		}
	}
}
