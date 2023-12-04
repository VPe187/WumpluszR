package hu.nye.progtech.wumpus.ui;

/**
 * Print messages and its parameters to console.
 */
public class Message {
    public static void printMessage(String format, Object... parameters) {
        System.out.print("  ");
        System.out.printf(format + "%n", parameters);
    }
}
