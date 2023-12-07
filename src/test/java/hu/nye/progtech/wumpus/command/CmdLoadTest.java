package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.JdbcGameSavesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdLoadTest {
    private static final String INPUT = "o";
    private static final String INPUT_OTHER = "b";
    private CmdLoad underTest;

    @BeforeEach
    public void setUp() {
        GameState gameState = new GameState(null, null, null);
        GameSavesRepository gameSavesRepository = new JdbcGameSavesRepository();
        underTest = new CmdLoad(gameSavesRepository, gameState);
    }

    @Test
    public void testIsCommandIsLoad() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotLoad() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }
}