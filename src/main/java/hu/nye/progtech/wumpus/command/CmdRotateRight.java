package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;

/**
 * Rotate right command.
 */
public class CmdRotateRight implements Command {
    private static final String COMMAND = "r";
    private final GameState gameState;

    public CmdRotateRight(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameState.getCurrentBoard().getHeroCell().rotateRight();
    }
}
