package com.armend.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.armend.game.components.ComputerPlayer;
import com.armend.game.rules.DecisionRules;
import com.armend.game.rules.StandardDecisionRules;

class ArbiterTest {

    private static final String NULL_CONSTRUCTOR_PARAMS_ERROR_MESSAGE = "Should not have reached this point. All constructor parameters must be non-null.";

    @Test
    void testNonNullDecisionRules() {
        try {
            new Arbiter(null, null, null);
            fail(NULL_CONSTRUCTOR_PARAMS_ERROR_MESSAGE);
        } catch (NullPointerException ne) {
            assertEquals(getExpectedMessage("decisionRules"), ne.getMessage());
        }
    }

    @Test
    void testNonNullPlayer1() {
        try {
            new Arbiter(new StandardDecisionRules(), null, null);
            fail(NULL_CONSTRUCTOR_PARAMS_ERROR_MESSAGE);
        } catch (NullPointerException ne) {
            assertEquals(getExpectedMessage("player1"), ne.getMessage());
        }
    }

    @Test
    void testNonNullPlayer2() {
        try {
            new Arbiter(new StandardDecisionRules(), new ComputerPlayer("Computer"), null);
            fail(NULL_CONSTRUCTOR_PARAMS_ERROR_MESSAGE);
        } catch (NullPointerException ne) {
            assertEquals(getExpectedMessage("player2"), ne.getMessage());
        }
    }

    @Test
    void testSetNullDecisionRules() {
        Arbiter arbiter = new Arbiter(new StandardDecisionRules(), new ComputerPlayer("Computer1"), new ComputerPlayer("Computer2"));

        arbiter.setDecisionRules(null);

        assertNotNull(arbiter.getDecisionRules());
    }

    @Test
    void testSetNewDecisionRules() {
        DecisionRules decisionRules1 = new StandardDecisionRules();
        DecisionRules decisionRules2 = new StandardDecisionRules();
        Arbiter arbiter = new Arbiter(decisionRules1, new ComputerPlayer("C1"), new ComputerPlayer("C2"));

        assertEquals(decisionRules1, arbiter.getDecisionRules());
        arbiter.setDecisionRules(decisionRules2);

        assertEquals(decisionRules2, arbiter.getDecisionRules());
    }

    private String getExpectedMessage(String parameter) {
        return "NULL value for '" + parameter + "' is not allowed";
    }
}
