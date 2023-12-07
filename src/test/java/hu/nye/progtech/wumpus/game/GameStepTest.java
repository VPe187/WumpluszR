package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.Direction;
import hu.nye.progtech.wumpus.model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        hero.setSight(Direction.NORTH);
        Cell targetCell = gameStep.canHeroMove(gameState);
        // when
        gameStep.moveHeroTo(gameState, hero, targetCell);
        // then
        assertEquals(startRow - 1, hero.getCurrentCell().getRow());
    }

    @Test
    public void testMoveToEastEmptyCell() {
        // given
        Hero hero = gameState.getCurrentBoard().getHero();
        hero.setSight(Direction.EAST);
        Cell targetCell = gameStep.canHeroMove(gameState);
        // when
        gameStep.moveHeroTo(gameState, hero, targetCell);
        // then
        assertEquals(startCol + 1, hero.getCurrentCell().getCol());
    }

    @Test
    public void testMoveToWallCell() {
        // given
        Hero hero = gameState.getCurrentBoard().getHero();
        hero.setSight(Direction.WEST);
        Cell targetCell = gameStep.canHeroMove(gameState);
        // when
        gameStep.moveHeroTo(gameState, hero, targetCell);
        // then
        assertEquals(startCol, hero.getCurrentCell().getCol());
    }
}