package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.game.GameStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link CmdShoot}
 */
class CmdShootTest {
    private static final String INPUT = "s";
    private static final String INPUT_OTHER = "o";
    private CmdShoot underTest;

    @BeforeEach
    public void setUp() {
        GameState gameState = new GameState(null, null, null);
        GameStep gameStep = new GameStep();
        underTest = new CmdShoot(gameState, gameStep);
    }

    @Test
    public void testIsCommandIsShoot() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotShoot() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }

}