package com.armend.game;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreBoard {

    private final List<Record> records;
    private final String firstPlayer;
    private final String secondPlayer;
    private int firstPlayersTotalScore;
    private int secondPlayersTotalScore;
    private int ties;
    private static final int COLUMN_WIDTH = -35;
    private static final String ROW_FORMAT = "| %" + COLUMN_WIDTH + "s | %" + COLUMN_WIDTH + "s | %" + COLUMN_WIDTH + "s |";
    private final String horizontalTableBorder;

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
        this.horizontalTableBorder = createHorizontalTableBorder();
    }

    public void printTo(PrintStream stream) {
        Objects.requireNonNull(stream, "Please provide a non-null PrintStream.");

        printScoreBoard(stream);
        printTotals(stream);
    }

    public String getLast() {
        if (records.isEmpty()) {
            return "";
        }
        var singleRecord = records.get(records.size() - 1);
        return String.format(" [%s: %s; %s: %s]", firstPlayer, singleRecord.getFirstPlayersChoice(), secondPlayer, singleRecord.getSecondPlayersChoice());
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

    private String createHorizontalTableBorder() {
        return "+" + "-".repeat(3 * Math.abs(ScoreBoard.COLUMN_WIDTH) + 8) + "+";
    }

    private void printTableHeader(PrintStream stream) {
        stream.printf((ROW_FORMAT) + "%n", firstPlayer, secondPlayer, "Winner");
    }

    private void printHorizontalBorder(PrintStream stream) {
        stream.println(horizontalTableBorder);
    }

    private void printTitle(PrintStream stream, String title) {
        stream.println("\n" + title);
    }

    private void printScoreBoard(PrintStream stream) {
        printTitle(stream, "Score Board:");
        printHorizontalBorder(stream);
        printTableHeader(stream);
        printHorizontalBorder(stream);
        printAllRecords(stream);
        printHorizontalBorder(stream);
    }

    private void printTotals(PrintStream stream) {
        printTitle(stream, "Totals:");
        printHorizontalBorder(stream);
        printTotalsHeader(stream);
        printHorizontalBorder(stream);
        printTotalsRow(stream);
        printHorizontalBorder(stream);
    }

    private void printTotalsRow(PrintStream stream) {
        stream.printf((ROW_FORMAT) + "%n", firstPlayersTotalScore, secondPlayersTotalScore, ties);
    }

    private void printTotalsHeader(PrintStream stream) {
        stream.printf((ROW_FORMAT) + "%n", firstPlayer, secondPlayer, "Ties");
    }

    private void printAllRecords(PrintStream stream) {
        for (var singleRecord : records) {
            stream.printf((ROW_FORMAT) + "%n", singleRecord.getFirstPlayersChoice(), singleRecord.getSecondPlayersChoice(), singleRecord.getWinner());
        }
    }

    private static class Record {
        private final String firstPlayersChoice;
        private final String secondPlayersChoice;
        private final String winner;

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
