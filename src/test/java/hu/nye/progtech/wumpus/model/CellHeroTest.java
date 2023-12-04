package hu.nye.progtech.wumpus.model;

import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.command.CmdRotateLeft;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CellHeroTest {
    private static final Direction initialDirection = Direction.EAST;
    private static final Direction afterLeftDirection_1 = Direction.NORTH;
    private static final Direction afterLeftDirection_2 = Direction.WEST;
    private static final Direction afterLeftDirection_3 = Direction.SOUTH;
    private static final Direction afterLeftDirection_4 = Direction.EAST;
    private CellHero underTest;
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
        underTest = gameState.getCurrentBoard().getHeroCell();
    }

    @Test
    public void testInitialDirection() {
        // given
        // when
        // then
        assertEquals(initialDirection, underTest.getSight());
    }

    @Test
    public void testHeroTurnRightFirst() {
        // given
        // when
        gameState.getCurrentBoard().getHeroCell().rotateLeft();
        // then
        assertEquals(afterLeftDirection_1, underTest.getSight());
    }

    @Test
    public void testHeroTurnRightSecond() {
        // given
        // when
        gameState.getCurrentBoard().getHeroCell().rotateLeft();
        // then
        assertEquals(afterLeftDirection_2, underTest.getSight());
    }

    @Test
    public void testHeroTurnRightThird() {
        // given
        // when
        gameState.getCurrentBoard().getHeroCell().rotateLeft();
        // then
        assertEquals(afterLeftDirection_3, underTest.getSight());
    }
    @Test
    public void testHeroTurnRightForth() {
        // given
        // when
        gameState.getCurrentBoard().getHeroCell().rotateLeft();
        // then
        assertEquals(afterLeftDirection_4, underTest.getSight());
    }
    @Test
    public void testHeroTurnRightToInitialPosition() {
        // given
        // when
        gameState.getCurrentBoard().getHeroCell().rotateLeft();
        // then
        assertEquals(initialDirection, underTest.getSight());
    }
}