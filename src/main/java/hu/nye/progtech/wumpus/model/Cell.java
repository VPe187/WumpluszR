package hu.nye.progtech.wumpus.model;

import hu.nye.progtech.wumpus.util.BoardUtil;

/**
 * This class represent one field of board.
 */
public class Cell {
    private final CellType type;
    private int col;
    private int row;

    public Cell(int col, int row, CellType value) {
        this.type = value;
        this.col = col;
        this.row = row;
    }

    public CellType getType() {
        return type;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return BoardUtil.letterFromInteger(col) + row;
    }
}
