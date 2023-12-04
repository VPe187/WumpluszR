package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdQuitTest {
    private static final String INPUT = "q";
    private static final String INPUT_OTHER = "m";
    private GameState gameState;
    private CmdQuit underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(null, null, null);
        underTest = new CmdQuit(gameState);
    }

    @Test
    public void testIsCommandIsQuit() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotQuit() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }

    @Test
    public void testGameRunningWhenInputIsNotExit() {
        // given
        gameState = new GameState(null, null, null);
        // when
        underTest.process(INPUT_OTHER);
        // then
        assertTrue(gameState.isRunning());
    }
}