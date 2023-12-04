package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.ui.Message;

/**
 * Restart command.
 */
public class CmdRestart implements Command {
    private static final String COMMAND = "t";
    private final GameState gameState;

    public CmdRestart(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameState.getCurrentBoard().reset();
        gameState.setSteps(0);
        Message.printMessage("The map has been restored to its initial state.");
    }
}
