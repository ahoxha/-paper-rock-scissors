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
		int columnWidth = -35;
		StringBuilder builder = new StringBuilder();
		builder.append("+");
		for (int i = 0; i < 3 * Math.abs(columnWidth) + 8; i++) {
			builder.append("-");
		}
		builder.append("+");
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
