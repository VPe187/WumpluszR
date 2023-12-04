package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;

/**
 * Rotate left command.
 */
public class CmdRotateLeft implements Command {
    private static final String COMMAND = "l";
    private final GameState gameState;

    public CmdRotateLeft(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameState.getCurrentBoard().getHeroCell().rotateLeft();
    }
}
