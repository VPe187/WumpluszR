package hu.nye.progtech.wumpus.board;

import hu.nye.progtech.wumpus.model.Cell;

/**
 * This class is the board of game.
 */
public class Board {
    int size;
    Cell[][] cells;

    public Board() {

    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOneCell(int col, int row, Cell cell) {
        cells[col][row] = cell;
    }
}
