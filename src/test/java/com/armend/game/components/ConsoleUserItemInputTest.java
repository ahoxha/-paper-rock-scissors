package com.armend.game.components;

import static com.armend.game.components.Item.PAPER;
import static com.armend.game.components.Item.ROCK;
import static com.armend.game.components.Item.SCISSORS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.Reader;
import java.io.StringReader;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ConsoleUserItemInputTest {
    @Test
    void when_wrong_input_is_provided_then_expect_null() {
        Reader reader = new StringReader("A");

        ConsoleUserItemInput input = new ConsoleUserItemInput(reader);

        assertThat(input.get()).isNull();
    }

    @ParameterizedTest(name = "Input [{0}] must be converted into {1}")
    @MethodSource("getInputArguments")
    void when_user_input_is_correct_then_expect_correct_Item(String userInput, Item... expectedOutput) {
        Reader reader = new StringReader(userInput);

        ConsoleUserItemInput input = new ConsoleUserItemInput(reader);

        assertOrderAndEqualityOfInputItems(input, expectedOutput);
    }

    private void assertOrderAndEqualityOfInputItems(ItemInput input, Item... items) {
        for (Item item : items) {
            assertThat(input.get()).isSameAs(item);
        }
    }

    static Stream<Arguments> getInputArguments() {
        return Stream.of( //
                arguments(readUserInput("R", "S", "P"), toArray(ROCK, SCISSORS, PAPER)), //
                arguments(readUserInput("r", "s", "p"), toArray(ROCK, SCISSORS, PAPER)), //
                arguments(readUserInput("Rock", "Scissors", "Paper"), toArray(ROCK, SCISSORS, PAPER)),//
                arguments(readUserInput("Paper", "Rock", "Scissors"), toArray(PAPER, ROCK, SCISSORS)),//
                arguments(readUserInput("ROCK", "SCISSORS", "PAPER"), toArray(ROCK, SCISSORS, PAPER)), //
                arguments(readUserInput("Rock", "sCiSSors", "PapeR"), toArray(ROCK, SCISSORS, PAPER)),//
                arguments(readUserInput("scissors", "rock", "paper"), toArray(SCISSORS, ROCK, PAPER)), //
                arguments(readUserInput("Scissors", "R", "r", "p", "Paper"), toArray(SCISSORS, ROCK, ROCK, PAPER, PAPER)), //
                arguments(readUserInput("R", "R"), toArray(ROCK, ROCK)), //
                arguments(readUserInput("S"), toArray(SCISSORS)) //
        );
    }

    private static String readUserInput(String... input) {
        return String.join("\n", input);
    }

    private static Item[] toArray(Item... items) {
        return items;
    }
}
