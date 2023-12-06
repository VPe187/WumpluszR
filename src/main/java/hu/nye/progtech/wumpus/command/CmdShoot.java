package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.game.GameStep;

/**
 * Shoot command.
 */
public class CmdShoot implements Command {
    private static final String COMMAND = "s";
    private final GameState gameState;
    private final GameStep gameStep;

    public CmdShoot(GameState gameState, GameStep gameStep) {
        this.gameState = gameState;
        this.gameStep = gameStep;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameStep.shoot(gameState);
    }
}
