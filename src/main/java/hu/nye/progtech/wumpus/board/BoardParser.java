package hu.nye.progtech.wumpus.board;

import java.util.Arrays;

/**
 * Parse raw board data and generate {@link Board}.
 */
public class BoardParser {
    final BoardRaw boardRaw;
    Board board;

    /**
     * Constructor of BoardParser.
     *
     * @param boardRaw of {@link BoardRaw}
     */
    public BoardParser(BoardRaw boardRaw) {
        this.boardRaw = boardRaw;
    }

    /**
     * Parse raw data from {@link BoardRaw} and create game main board as {@link Board}.
     *
     * @return board as {@link Board}
     */
    public Board parseRawBoard() {
        parseFirstRow();
        parseRemainingRows();
        return board;
    }

    private void parseFirstRow() {
        String[] row = boardRaw.getFirstRow().split(" ");
        int boardSize = Integer.parseInt(row[0]);
        int startCol = 1; // B
        int startRow = Integer.parseInt(row[2]);
        board = Board.builder().withSize(boardSize).withStartCol(startCol).withStartRow(startRow).build();
    }

    private void parseRemainingRows() {
        //System.out.println(boardRaw.getRemainingRows());
    }
}
