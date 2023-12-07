package hu.nye.progtech.wumpus.board;

import java.util.List;

/**
 * BoardRaw is a class using store raw data from text file.
 */
public class BoardRaw {
    private final List<String> rows;

    public BoardRaw(List<String> rows) {
        this.rows = rows;
    }

    /**
     * Give the first parameters row from {@link BoardRaw}.
     *
     * @return first row of {@link BoardRaw} as String
     */
    public String getFirstRow() {
        return rows.get(0);
    }

    public List<String> getRemainingRows() {
        return rows.subList(1, rows.size());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String row : rows) {
            stringBuilder.append(row);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
