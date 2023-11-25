package hu.nye.progtech.wumpus.exception;

/**
 * If reading board fail, throws this exception.
 */
public class BoardReadingException extends Exception {
    public BoardReadingException(String message) {
        super(message);
    }
}
