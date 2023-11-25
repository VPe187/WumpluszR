package hu.nye.progtech.wumpus.board;

import java.util.regex.Pattern;

import hu.nye.progtech.wumpus.BoardUtil;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;

/**
 * Parse raw board data and generate {@link Board}.
 */
public class BoardParser {
    private static final String VALID_SIZE_REGEX = "[0-9]+";
    private static final String VALID_HERO_COL_REGEX = "[A-Z]";
    private static final String VALID_HERO_ROW_REGEX = "[0-9]";
    private static final String VALID_HERO_SIGHT_REGEX = "[NSEW]";
    private static final String VALID_ROW_LETTERS = "[WUGP_]+";
    final BoardRaw boardRaw;
    Board board;
    private int boardSize;
    private int startCol;
    private int startRow;
    private Direction direction;
    private Cell[][] cells;

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
    public Board parseRawBoard() throws BoardParsingException {
        parseFirstRow();
        parseRemainingRows();
        return Board.builder().withSize(boardSize).withStartCol(startCol).withStartRow(startRow).build();
    }

    private void parseFirstRow() throws BoardParsingException {
        String[] row = boardRaw.getFirstRow().split(" ");
        if (row.length != 4) {
            throw new BoardParsingException("Header contains invalid number of characters!");
        }
        if (!Pattern.matches(VALID_SIZE_REGEX, row[0])) {
            throw new BoardParsingException("Header size value contains invalid character!");
        } else {
            boardSize = Integer.parseInt(row[0]);
        }
        if (!Pattern.matches(VALID_HERO_COL_REGEX, row[1])) {
            throw new BoardParsingException("Header hero column value contains invalid character!");
        } else {
            startCol = BoardUtil.integerFromLetter(row[1]);
        }
        if (!Pattern.matches(VALID_HERO_ROW_REGEX, row[2])) {
            throw new BoardParsingException("Header hero column value contains invalid character!");
        } else {
            startRow = Integer.parseInt(row[2]);
        }
        if (!Pattern.matches(VALID_HERO_SIGHT_REGEX, row[3])) {
            throw new BoardParsingException("Header hero sight value contains invalid character!");
        } else {
            direction = Direction.getByValue(row[3]);
        }
    }

    private Cell[][] parseRemainingRows() throws BoardParsingException {
        cells = new Cell[boardSize][boardSize];
        int i = 0;
        for (String row : boardRaw.getRemainingRows()) {
            if (!Pattern.matches(VALID_ROW_LETTERS, row)) {
                throw new BoardParsingException("Row contains invalid character!");
            } else {
                for (int j = 0; j < row.length(); j++) {
                    cells[j][i] = new Cell(CellType.getByValue(row.split("")[j]));
                }
            }
            i++;
        }
        cells[startCol - 1][startRow - 1] = new Cell(CellType.HERO);
        return cells;

    }
}
