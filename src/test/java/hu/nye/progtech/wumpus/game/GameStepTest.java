package hu.nye.progtech.wumpus.game;

import java.util.List;

import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;
import hu.nye.progtech.wumpus.model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameStepTest {
    @Mock
    private BoardRaw boardRaw;
    @Mock
    private BoardParser boardParser;
    @Mock
    private GameState gameState;
    @Mock
    private GameStep gameStep;
    private final List<String> rows = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W___PW",
            "WUGP_W",
            "W____W",
            "W__P_W",
            "WWWWWW"
    );
    private final int startCol = 1;
    private final int startRow = 4;
    private final int wumpusCol = 1;
    private final int wumpusRow = 2;

    @BeforeEach
    public void setUp() throws BoardParsingException {
        boardRaw = new BoardRaw(rows);
        boardParser = new BoardParser(boardRaw);
        gameState = new GameState(boardParser.parseRawBoard(), null, null);
        gameStep = new GameStep();
    }

    @Test
    public void testMoveToNorthEmptyCell() {
        // given
        Hero hero = gameState.getCurrentBoard().getHero();
        // when
        hero.setSight(Direction.NORTH);
        Cell underTest = gameStep.getTargetCell(gameState);
        // then
        assertNotEquals(null, underTest);
    }

    @Test
    public void testMovementToNorthEmptyCell() {
        // given
        Hero hero = gameState.getCurrentBoard().getHero();
        // when
        hero.setSight(Direction.NORTH);
        Cell targetCell = gameStep.getTargetCell(gameState);
        gameStep.moveHeroTo(gameState, hero, targetCell);
        Cell underTest = hero.getCurrentCell();
        assertEquals(startRow - 1, underTest.getRow());
        assertEquals(startCol, underTest.getCol());
    }

    @Test
    public void testMoveToWestWallCell() {
        // given
        Hero hero = gameState.getCurrentBoard().getHero();
        // when
        hero.setSight(Direction.WEST);
        Cell underTest = gameStep.getTargetCell(gameState);
        // then
        assertNull(underTest);
    }

    @Test
    public void testShoot() {
        // given
        Hero hero = gameState.getCurrentBoard().getHero();
        // when
        hero.setSight(Direction.NORTH);
        gameStep.shoot(gameState);
        CellType underTest = gameState.getCurrentBoard().getCell(wumpusCol, wumpusRow).getType();
        // then
        assertEquals(CellType.EMPTY, underTest);
    }
}