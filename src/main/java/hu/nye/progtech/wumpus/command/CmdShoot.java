package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.game.GameMove;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.model.CellHero;

/**
 * Shoot command.
 */
public class CmdShoot implements Command {
    private static final String COMMAND = "s";
    private final GameState gameState;

    public CmdShoot(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        GameMove.shoot(gameState);
    }
}
