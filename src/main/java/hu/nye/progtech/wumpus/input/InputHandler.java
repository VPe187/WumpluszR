package hu.nye.progtech.wumpus.input;

import java.util.List;

import hu.nye.progtech.wumpus.command.Command;

/**
 * This class handle user inputs.
 */
public class InputHandler {
    private final List<Command> availableCommands;

    public InputHandler(List<Command> availableCommands) {
        this.availableCommands = availableCommands;
    }

    /**
     * Check the command.
     */
    public void handleInput(String input) {
        for (Command command : this.availableCommands) {
            if (command.validateCommand(input)) {
                command.process(input);
                break;
            }
        }
    }
}
