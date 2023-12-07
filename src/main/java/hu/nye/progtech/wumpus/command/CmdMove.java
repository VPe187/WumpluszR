package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.game.GameStep;
import hu.nye.progtech.wumpus.model.Cell;

/**
 * Move command.
 */
public class CmdMove implements Command {
    private static final String COMMAND = "m";
    private final GameState gameState;
    private final GameStep gameStep;

    public CmdMove(GameState gameState, GameStep gameStep) {
        this.gameState = gameState;
        this.gameStep = gameStep;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        Board board = gameState.getCurrentBoard();
        Cell targetCell = gameStep.canHeroMove(gameState);
        if (targetCell == null) {
            System.out.println("This move not possible because target cell contains wall.");
        } else {
            gameStep.moveHeroTo(gameState, board.getHero(), targetCell);
            gameStep.move(gameState, targetCell);
        }
    }
}
