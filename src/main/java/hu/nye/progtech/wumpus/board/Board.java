package hu.nye.progtech.wumpus.board;

import hu.nye.progtech.wumpus.model.Cell;

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
    Cell[][] cells;

    public Board(int size, int startCol, int startRow) {
        this.size = size;
        this.startRow = startRow;
        this.startCol = startCol;
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

    /**
     * Builder for {@link Board}
     */
    public static final class BoardBuilder {
        private int size;
        private int startCol;
        private int startRow;

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

        public Board build() {
            return new Board(size, startCol, startRow);
        }
    }
}
