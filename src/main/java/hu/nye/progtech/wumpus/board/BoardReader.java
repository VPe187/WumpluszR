package hu.nye.progtech.wumpus.board;

import java.util.List;

import hu.nye.progtech.wumpus.exception.BoardReadingException;

/**
 * Interface reads board data raw format.
 */
public interface BoardReader {
    /**
     * Interface reads board data raw format.
     *
     * @return a list of strings, each representing a single row of a map
     * @throws BoardReadingException when reading of the map fails
     */
    List<String> readBoard() throws BoardReadingException;
}
