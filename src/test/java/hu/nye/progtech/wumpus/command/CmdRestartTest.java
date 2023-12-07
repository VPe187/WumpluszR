package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdRestartTest {
    private static final String INPUT = "t";
    private static final String INPUT_OTHER = "r";
    private CmdRestart underTest;

    @BeforeEach
    public void setUp() {
        GameState gameState = new GameState(null, null, null);
        underTest = new CmdRestart(gameState);
    }

    @Test
    public void testIsCommandIsRestart() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotRestart() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }
}