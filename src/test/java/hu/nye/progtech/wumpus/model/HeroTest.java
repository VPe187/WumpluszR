package hu.nye.progtech.wumpus.model;

import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.game.GameStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link Hero}
 */
@ExtendWith(MockitoExtension.class)
class HeroTest {
    private static final Direction initialDirection = Direction.EAST;
    private static final Direction afterLeftDirection_1 = Direction.NORTH;
    private static final Direction afterLeftDirection_2 = Direction.WEST;
    private static final Direction afterLeftDirection_3 = Direction.SOUTH;
    @Mock
    private BoardRaw boardRaw;
    @Mock
    private BoardParser boardParser;
    @Mock
    private GameState gameState;
    @Mock
    private GameStep gameStep;
    private Hero underTest;
    private final List<String> rows = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W___PW",
            "WUGP_W",
            "W____W",
            "W__P_W",
            "WWWWWW"
    );

    private final int WUMPUS_COL = 1;
    private final int WUMPUS_ROW = 2;

    @BeforeEach
    public void setUp() throws BoardParsingException {
        boardRaw = new BoardRaw(rows);
        boardParser = new BoardParser(boardRaw);
        gameState = new GameState(boardParser.parseRawBoard(), null, null);
        gameStep = new GameStep();
        underTest = gameState.getCurrentBoard().getHero();
    }

    @Test
    public void testInitialDirection() {
        // given
        underTest = gameState.getCurrentBoard().getHero();
        // when
        Direction result = underTest.getSight();
        // then
        assertEquals(initialDirection, result);
    }

    @Test
    public void testHeroTurnRightOneTime() {
        // given
        underTest = gameState.getCurrentBoard().getHero();
        // when
        underTest.rotateLeft();
        Direction result = underTest.getSight();
        // then
        assertEquals(afterLeftDirection_1, result);
    }

    @Test
    public void testHeroTurnRightTwoTimes() {
        // given
        underTest = gameState.getCurrentBoard().getHero();
        // when
        underTest.rotateLeft();
        underTest.rotateLeft();
        Direction result = underTest.getSight();
        // then
        assertEquals(afterLeftDirection_2, result);
    }

    @Test
    public void testHeroTurnRightThreeTimes() {
        // given
        underTest = gameState.getCurrentBoard().getHero();
        // when
        underTest.rotateLeft();
        underTest.rotateLeft();
        underTest.rotateLeft();
        Direction result = underTest.getSight();
        // then
        assertEquals(afterLeftDirection_3, result);
    }

    @Test
    public void testHeroTurnRightFourTimes() {
        // given
        underTest = gameState.getCurrentBoard().getHero();
        // when
        underTest.rotateLeft();
        underTest.rotateLeft();
        underTest.rotateLeft();
        underTest.rotateLeft();
        Direction result = underTest.getSight();
        // then
        assertEquals(initialDirection, result);
    }

    @Test
    public void testHeroDiedWhenMeetWumpus() {
        // given
        underTest = gameState.getCurrentBoard().getHero();
        // when
        underTest.rotateLeft();
        Cell targetCell;
        targetCell = gameStep.getTargetCell(gameState);
        gameStep.move(gameState, targetCell);
        targetCell = gameStep.getTargetCell(gameState);
        gameStep.move(gameState, targetCell);
        Boolean result = gameState.getCurrentBoard().getHero().getDead();
        // then
        assertEquals(true, result);

    }
}