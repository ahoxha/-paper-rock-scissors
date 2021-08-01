package com.armend.game.components;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class ComputerPlayerTest {

    @Test
    void when_null_name_and_null_input_are_provided_then_expect_exception() {
        assertThatExceptionOfType(IllegalArgumentException.class) //
                .isThrownBy(() -> new ComputerPlayer(null, null)) //
                .withMessage("The 'name' argument must be non-null and non-empty.");
    }

    @Test
    void when_nonnull_name_and_null_input_are_provided_then_expect_exception() {
        assertThatExceptionOfType(NullPointerException.class) //
                .isThrownBy(() -> new ComputerPlayer("Computer", null)) //
                .withMessage("The 'input' argument must not be null.");
    }
}
