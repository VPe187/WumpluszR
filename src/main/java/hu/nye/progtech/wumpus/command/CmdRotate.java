package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;

/**
 * This rotate Hero.
 */
public class CmdRotate implements Command {
    private static final String COMMAND = "r";
    private final GameState gameState;

    public CmdRotate(GameState gameState) {
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
