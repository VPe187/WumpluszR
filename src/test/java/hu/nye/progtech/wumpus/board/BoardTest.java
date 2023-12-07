package hu.nye.progtech.wumpus.board;

import hu.nye.progtech.wumpus.model.CellType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hu.nye.progtech.wumpus.model.Cell;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoardTest {
    private final int BOARD_SIZE_EASY = 6;
    private final int BOARD_SIZE_MEDIUM = 13;
    private final int BOARD_SIZE_HARD = 18;
    private final int WUMPUSES_EASY = 1;
    private final int WUMPUSES_MEDIUM = 2;
    private final int WUMPUSES_HARD = 3;
    private Cell[][] cells;

    @BeforeEach
    public void setUp() {
        cells = new Cell[1][1];
        cells[0][0] = new Cell(0, 0, CellType.EMPTY);
    }
    @Test
    public void testEasyWumpusCount() {
        // when
        Board board = new Board(BOARD_SIZE_EASY, BOARD_SIZE_EASY, cells);
        // given
        int result = board.getWumpusCountByWorldSize(BOARD_SIZE_EASY);
        // then
        assertEquals(WUMPUSES_EASY, result);
    }
    @Test
    public void testMediumWumpusCount() {
        // when
        Board board = new Board(BOARD_SIZE_MEDIUM, BOARD_SIZE_MEDIUM, cells);
        // given
        int result = board.getWumpusCountByWorldSize(BOARD_SIZE_MEDIUM);
        // then
        assertEquals(WUMPUSES_MEDIUM, result);
    }

    @Test
    public void testHardWumpusCount() {
        // when
        Board board = new Board(BOARD_SIZE_HARD, BOARD_SIZE_HARD, cells);
        // given
        int result = board.getWumpusCountByWorldSize(BOARD_SIZE_HARD);
        // then
        assertEquals(WUMPUSES_HARD, result);
    }
}