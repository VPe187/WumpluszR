package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;

/**
 * Indicate one move with Hero.
 */
public class GameMove {

    public GameMove() {
    }

    public static void move(GameState gameState, Cell targetCell) {
        Board board = gameState.getCurrentBoard();
        gameState.setSteps(gameState.getSteps() + 1);
        if (targetCell.getType().equals(CellType.WUMPUS)) {
            System.out.println("Your hero met a WUMPUS and died.");
            board.getHeroCell().setDead(true);
            gameState.setRunning(false);
        } else if (targetCell.getType().equals(CellType.GOLD)) {
            System.out.println("Your hero pick up the gold.");
            board.getHeroCell().setHasGold(true);
        } else if (targetCell.getType().equals(CellType.PIT)) {
            System.out.println("Your hero has fallen into the pit and lost 1 arrow.");
            board.getHeroCell().loseArrow();
        } else {
            System.out.println("The hero has just moved to " + targetCell + " field.");
            if (board.getHeroCell().checkGoal()) {
                System.out.println("You got the gold and you got out. You win by " + gameState.getSteps() + " steps.");
                gameState.setRunning(false);
            }
        }
        board.moveHeroTo(board.getHeroCell(), targetCell);
        System.out.println("Target: " + targetCell);
    }
}
