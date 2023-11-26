package hu.nye.progtech.wumpus.board;

import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.ui.ConsolRenderer;

/**
 * This class is the board of game.
 */
public class Board {
    public static BoardBuilder builder() {
        return new BoardBuilder();
    }

    private final int size;
    private final int startRow;
    private final int startCol;
    private final Cell[][] cells;

    public Board(int size, int startCol, int startRow, Cell[][] cells) {
        this.size = size;
        this.startRow = startRow;
        this.startCol = startCol;
        this.cells = cells;
    }

    public void setOneCell(int col, int row, Cell cell) {
        cells[col][row] = cell;
    }

    public int getSize() {
        return size;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(cells[j][i].getType());
            }
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    /**
     * Builder for {@link Board}.
     */
    public static final class BoardBuilder {
        private int size;
        private int startCol;
        private int startRow;
        private Cell[][] cells;

        private BoardBuilder() {
        }

        public static BoardBuilder builder() {
            return new BoardBuilder();
        }

        public BoardBuilder withSize(int size) {
            this.size = size;
            return this;
        }

        public BoardBuilder withStartCol(int startCol) {
            this.startCol = startCol;
            return this;
        }

        public BoardBuilder withStartRow(int startRow) {
            this.startRow = startRow;
            return this;
        }

        public BoardBuilder withCells(Cell[][] cells) {
            this.cells = cells;
            return this;
        }

        public Board build() {
            return new Board(size, startCol, startRow, cells);
        }
    }
}
