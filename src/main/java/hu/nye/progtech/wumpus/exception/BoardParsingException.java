package hu.nye.progtech.wumpus.exception;

/**
 * If board parsing fail, throws this exception.
 */
public class BoardParsingException extends Exception {
    public BoardParsingException(String message) {
        super(message);
    }
}
