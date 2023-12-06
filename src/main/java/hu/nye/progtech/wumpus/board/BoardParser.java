package hu.nye.progtech.wumpus.board;

import java.util.regex.Pattern;

import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;
import hu.nye.progtech.wumpus.model.Hero;
import hu.nye.progtech.wumpus.util.BoardUtil;

/**
 * Parse raw board data and generate {@link Board}.
 */
public class BoardParser {
    private static final String VALID_SIZE_REGEX = "[0-9]+";
    private static final String VALID_HERO_COL_REGEX = "[A-Z]";
    private static final String VALID_HERO_ROW_REGEX = "[0-9]";
    private static final String VALID_HERO_SIGHT_REGEX = "[NSEW]";
    private static final String VALID_ROW_LETTERS = "[WUGP_]+";
    private final BoardRaw boardRaw;

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
        checkHeader();
        int boardSize = getBoardSize();
        int startCol = getStartColumn();
        int startRow = getStartRow();
        Direction heroSight = getHeroSight();
        Cell[][] cells = parseRemainingRows(boardSize);
        cells[startCol - 1][startRow - 1].setType(CellType.HERO);
        Hero hero = new Hero(cells[startCol - 1][startRow - 1], 1, false, startCol - 1, startRow - 1, heroSight);
        Board board = new Board(boardSize, boardSize, cells);
        board.setHero(hero);
        hero.setArrows(board.getWumpusCountByWorldSize(boardSize));
        return board;
    }

    private void checkHeader() throws BoardParsingException {
        String[] row = boardRaw.getFirstRow().split(" ");
        if (row.length != 4) {
            throw new BoardParsingException("Header contains invalid number of characters!");
        }
    }

    private int getBoardSize() throws BoardParsingException {
        int boardSize = 0;
        String[] row = boardRaw.getFirstRow().split(" ");
        if (!Pattern.matches(VALID_SIZE_REGEX, row[0])) {
            throw new BoardParsingException("Header size value contains invalid character!");
        } else {
            boardSize = Integer.parseInt(row[0]);
        }
        return boardSize;
    }

    private int getStartColumn() throws BoardParsingException {
        int startCol = 0;
        String[] row = boardRaw.getFirstRow().split(" ");
        if (!Pattern.matches(VALID_HERO_COL_REGEX, row[1])) {
            throw new BoardParsingException("Header hero column value contains invalid character!");
        } else {
            startCol = BoardUtil.integerFromLetter(row[1]);
        }
        return startCol;
    }

    private int getStartRow() throws BoardParsingException {
        int startRow = 0;
        String[] row = boardRaw.getFirstRow().split(" ");
        if (!Pattern.matches(VALID_HERO_ROW_REGEX, row[2])) {
            throw new BoardParsingException("Header hero row value contains invalid character!");
        } else {
            startRow = Integer.parseInt(row[2]);
        }
        return startRow;
    }

    private Direction getHeroSight() throws BoardParsingException {
        Direction direction;
        String[] row = boardRaw.getFirstRow().split(" ");
        if (!Pattern.matches(VALID_HERO_SIGHT_REGEX, row[3])) {
            throw new BoardParsingException("Header hero sight value contains invalid character!");
        } else {
            direction = Direction.getByValue(row[3]);
        }
        return direction;
    }

    private Cell[][] parseRemainingRows(int boardSize) throws BoardParsingException {
        Cell[][] cells = new Cell[boardSize][boardSize];
        int i = 0;
        for (String row : boardRaw.getRemainingRows()) {
            if (!Pattern.matches(VALID_ROW_LETTERS, row)) {
                throw new BoardParsingException("Row contains invalid character!");
            } else {
                for (int j = 0; j < row.length(); j++) {
                    cells[j][i] = new Cell(j, i, CellType.getByValue(row.split("")[j]));
                }
            }
            i++;
        }
        return cells;
    }
}
