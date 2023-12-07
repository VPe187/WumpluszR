package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.JdbcGameSavesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdSaveDBTest {
    private static final String INPUT = "d";
    private static final String INPUT_OTHER = "b";
    private CmdSaveDB underTest;

    @BeforeEach
    public void setUp() {
        GameState gameState = new GameState(null, null, null);
        GameSavesRepository gameSavesRepository = new JdbcGameSavesRepository();
        underTest = new CmdSaveDB(gameSavesRepository, gameState);
    }

    @Test
    public void testIsCommandIsSaveDB() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotSaveDb() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }
}