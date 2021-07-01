package com.armend.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.armend.game.components.ComputerPlayer;
import com.armend.game.rules.DecisionRules;
import com.armend.game.rules.StandardDecisionRules;

class ArbiterTest {
    @Test
    void testNonNullStrategy() {
        try {
            new Arbiter(null, null, null);
            fail("Should not have reached this point. All consturctor parameters must be non-null.");
        } catch (NullPointerException ne) {
            assertEquals("NULL value for 'decisionRules' is not allowed", ne.getMessage());
        }
    }

    @Test
    void testNonNullPlayer1() {
        try {
            new Arbiter(new StandardDecisionRules(), null, null);
            fail("Should not have reached this point. All consturctor parameters must be non-null.");
        } catch (NullPointerException ne) {
            assertEquals("NULL value for 'player1' is not allowed", ne.getMessage());
        }
    }

    @Test
    void testNonNullPlayer2() {
        try {
            new Arbiter(new StandardDecisionRules(), new ComputerPlayer("Computer"), null);
            fail("Should not have reached this point. All consturctor parameters must be non-null.");
        } catch (NullPointerException ne) {
            assertEquals("NULL value for 'player2' is not allowed", ne.getMessage());
        }
    }

    @Test
    void testSetNullStrategy() {
        Arbiter arbiter = new Arbiter(new StandardDecisionRules(), new ComputerPlayer("Computer1"), new ComputerPlayer("Computer2"));
        arbiter.setDecisionRules(null);
        assertNotNull(arbiter.getDecisionRules());
    }

    @Test
    void testSetNewStrategy() {
        DecisionRules strategy1 = new StandardDecisionRules();
        DecisionRules strategy2 = new StandardDecisionRules();
        Arbiter arbiter = new Arbiter(strategy1, new ComputerPlayer("C1"), new ComputerPlayer("C2"));
        assertEquals(strategy1, arbiter.getDecisionRules());
        arbiter.setDecisionRules(strategy2);
        assertEquals(strategy2, arbiter.getDecisionRules());
    }
}
