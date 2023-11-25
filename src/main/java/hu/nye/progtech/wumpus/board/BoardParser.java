package hu.nye.progtech.wumpus.board;

/**
 * Parse raw board data and generate {@link Board}.
 */
public class BoardParser {
    BoardRaw boardRaw;
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
        System.out.println(this.boardRaw.getFirstRow());
    }

    private void parseRemainingRows() {
        System.out.println(boardRaw.getRemainingRows());
    }
}
