package hu.nye.progtech.wumpus.board;

import java.util.Arrays;
import java.util.Objects;

import hu.nye.progtech.wumpus.model.Cell;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Persis table board.
 */
@XmlRootElement(name = "game_state")
public class PersistableBoard {
    private int colSize;
    private int rowSize;
    private Cell[][] cells;

    public PersistableBoard() {
    }

    public PersistableBoard(int colSize, int rowSize, Cell[][] cells) {
        this.colSize = colSize;
        this.rowSize = rowSize;
        this.cells = cells;
    }

    public int getColSize() {
        return colSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersistableBoard that = (PersistableBoard) o;
        return colSize == that.colSize && rowSize == that.rowSize && Arrays.deepEquals(cells, that.cells);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(colSize, rowSize);
        result = 31 * result + Arrays.deepHashCode(cells);
        return result;
    }
}
