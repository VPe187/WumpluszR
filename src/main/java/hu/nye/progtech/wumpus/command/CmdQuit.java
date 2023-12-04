package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;

/**
 * Quit command. This interrupt the game cycle.
 */
public class CmdQuit implements Command {
    private static final String COMMAND = "q";
    private final GameState gameState;

    public CmdQuit(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameState.setRunning(false);
    }
}
