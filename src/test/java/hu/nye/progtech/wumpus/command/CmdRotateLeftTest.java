package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CmdRotateLeftTest {
    private static final String INPUT = "l";
    private static final String INPUT_OTHER = "r";
    private static final Direction initialDirection = Direction.EAST;
    private static final Direction afterLeftDirection_1 = Direction.NORTH;
    private static final Direction afterLeftDirection_2 = Direction.WEST;
    private static final Direction afterLeftDirection_3 = Direction.SOUTH;
    private static final Direction afterLeftDirection_4 = Direction.EAST;
    private CmdRotateLeft underTest;
    private GameState gameState;
    private final List<String> rows = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W___PW",
            "WUGP_W",
            "W____W",
            "W__P_W",
            "WWWWWW"
    );

    @BeforeEach
    public void setUp() throws BoardParsingException {
        BoardRaw boardRaw = new BoardRaw(rows);
        BoardParser boardParser = new BoardParser(boardRaw);
        gameState = new GameState(boardParser.parseRawBoard(), null, null);
        underTest = new CmdRotateLeft(gameState);
    }

    @Test
    public void testInitialDirection() {
        // given
        // when
        // then
        assertEquals(initialDirection, gameState.getCurrentBoard().getHeroCell().getSight());
    }

    @Test
    public void testCanValidationShuldReturnTrueWhenInputIsLeft() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT);
        // then
        assertTrue(result);
    }

    @Test
    public void testCanValidationShuldReturnFalseWhenInputIsNotLeft() {
        // given
        // when
        boolean result = underTest.validateCommand(INPUT_OTHER);
        // then
        assertFalse(result);
    }


 }