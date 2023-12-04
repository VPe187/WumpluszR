package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.game.GameMove;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellHero;
import hu.nye.progtech.wumpus.model.CellType;

/**
 * Move Hero to next cell if Hero can move to.
 */
public class CmdMove implements Command {
    private static final String COMMAND = "m";
    private final GameState gameState;

    public CmdMove(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        Board board = gameState.getCurrentBoard();
        Cell targetCell = board.canHeroMove();
        if (targetCell == null) {
            System.out.println("This move not possible because target cell contains wall.");
        } else {
            board.moveHeroTo(board.getHeroCell(), targetCell);
            GameMove.move(gameState, targetCell);
        }
    }
}
