package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdMoveTest {
    private static final String INPUT = "m";
    private static final String INPUT_OTHER = "q";
    private CmdMove underTest;

    @BeforeEach
    public void setUp() {
        GameState gameState = new GameState(null, null, null);
        underTest = new CmdMove(gameState);
    }

    @Test
    public void testIsCommandIsMove() {
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

}