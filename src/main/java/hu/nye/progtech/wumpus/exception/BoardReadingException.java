package hu.nye.progtech.wumpus.exception;

/**
 * If reading map fail, throws this exception.
 */
public class BoardReadingException extends Exception {
    public BoardReadingException(String message) {
        super(message);
    }
}
