package hu.nye.progtech.wumpus.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumpus.exception.BoardReadingException;

/**
 * Implementation of the {@link BoardReader} uses {@link java.io.BufferedReader}.
 */
public class BufferedBoardReader implements BoardReader {
    private final BufferedReader bufferedReader;

    public BufferedBoardReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<String> readBoard() throws BoardReadingException {
        String row;
        List<String> rows = new ArrayList<>();
        try {
            while ((row = bufferedReader.readLine()) != null) {
                rows.add(row);
            }
        } catch (IOException e) {
            throw new BoardReadingException("Error while reading board data.");
        }
        return rows;
    }
}
