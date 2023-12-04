package hu.nye.progtech.wumpus.command;

/**
 * Used to implement the commands that the user can use during the game.
 */
public interface Command {
    /**
     * Checks that the command is in the command list (executable).
     *
     * @param input as String user given input.
     * @return {@code true} or {@code false} depending on whether a command exists or not.
     */
    boolean validateCommand(String input);

    /**
     * Execute the given input as a command.
     *
     * @param input as String the command to process
     */
    void process(String input);

}