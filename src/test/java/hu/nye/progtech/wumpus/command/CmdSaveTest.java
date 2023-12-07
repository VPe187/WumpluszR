package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.XmlGameSavesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdSaveTest {
    private static final String INPUT = "v";
    private static final String INPUT_OTHER = "l";
    private CmdSave underTest;

    @BeforeEach
    public void setUp() {
        GameState gameState = new GameState(null, null, null);
        GameSavesRepository gameSavesRepository = new XmlGameSavesRepository();
        underTest = new CmdSave(gameSavesRepository, gameState);
    }

    @Test
    public void testIsCommandIsSave() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testIsCommandIsNotSave() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }
}