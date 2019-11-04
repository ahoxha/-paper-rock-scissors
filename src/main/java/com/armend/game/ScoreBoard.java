package com.armend.game;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreBoard {

	private List<Record> records;
	private String firstPlayer;
	private String secondPlayer;
	private int firstPlayersTotalScore;
	private int secondPlayersTotalScore;
	private int ties;
	private final int columnWidth = -35;
	private final String rowFormat = "| %" + columnWidth + "s | %" + columnWidth + "s | %" + columnWidth + "s |";

	public ScoreBoard(String firstPlayer, String secondPlayer) {
		if (firstPlayer == null || firstPlayer.isEmpty()) {
			throw new IllegalArgumentException("The 'firstPlayer' argument must be non-null and non-empty.");
		}
		if (secondPlayer == null || secondPlayer.isEmpty()) {
			throw new IllegalArgumentException("The 'secondPlayer' argument must be non-null and non-empty.");
		}
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		records = new ArrayList<>();
	}

	public void printTo(PrintStream stream) {
		Objects.requireNonNull(stream, "Please provide a non-null PrintStream.");
		StringBuilder builder = new StringBuilder();
		createHorizontalTableBorder(columnWidth, builder);
		printTitle(stream);
		printHorizontalBorder(stream, builder);
		printTableHeader(stream);
		printHorizontalBorder(stream, builder);
		printAllRecords(stream);
		stream.print(builder.toString());
		stream.println("\nTotals:");
		printHorizontalBorder(stream, builder);
		stream.println(String.format(rowFormat, firstPlayer, secondPlayer, "Ties"));
		printHorizontalBorder(stream, builder);
		stream.println(String.format(rowFormat, firstPlayersTotalScore, secondPlayersTotalScore, ties));
		printHorizontalBorder(stream, builder);
	}

	private void printAllRecords(PrintStream stream) {
		for (Record record : records) {
			stream.println(String.format(rowFormat, record.getFirstPlayersChoice(), record.getSecondPlayersChoice(),
					record.getWinner()));
		}
	}

	private void printTableHeader(PrintStream stream) {
		stream.println(String.format(rowFormat, firstPlayer, secondPlayer, "Winner"));
	}

	private void printHorizontalBorder(PrintStream stream, StringBuilder builder) {
		stream.println(builder.toString());
	}

	private void printTitle(PrintStream stream) {
		stream.println("\n================ Score Board =========================");
	}

	private void createHorizontalTableBorder(int columnWidth, StringBuilder builder) {
		builder.append("+");
		for (int i = 0; i < 3 * Math.abs(columnWidth) + 8; i++) {
			builder.append("-");
		}
		builder.append("+");
	}

	public String getLast() {
		if (records.isEmpty()) {
			return "";
		}
		Record r = records.get(records.size() - 1);
		return String.format(" [%s: %s; %s: %s]", firstPlayer, r.getFirstPlayersChoice(), secondPlayer,
				r.getSecondPlayersChoice());
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

	public void incrementTies() {
		ties++;
	}

	public int getFirstPlayersTotalScore() {
		return firstPlayersTotalScore;
	}

	public int getSecondPlayersTotalScore() {
		return secondPlayersTotalScore;
	}

	public int getTies() {
		return ties;
	}

	public String getFirstPlayer() {
		return firstPlayer;
	}

	public String getSecondPlayer() {
		return secondPlayer;
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
