package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellHero;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;
import hu.nye.progtech.wumpus.ui.Message;

/**
 * Indicate one move with Hero.
 */
public class GameMove {

    public GameMove() {
    }

    /**
     * Moving hero towerds its sight to the next field.
     *
     * @param gameState as {@link GameState}
     * @param targetCell as {@link Cell}
     */
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

    /**
     * Shoot arrow from Hero position to first collision.
     *
     * @param gameState as {@link GameState}
     */
    public static void shoot(GameState gameState) {
        Board board = gameState.getCurrentBoard();
        CellHero heroCell = board.getHeroCell();
        if (heroCell.getArrows() > 0) {
            heroCell.loseArrow();
            Cell targetCell = GameMove.shootEndCell(gameState, heroCell.getSight());
            if (targetCell.getType().equals(CellType.WUMPUS)) {
                board.getCells()[targetCell.getCol()][targetCell.getRow()] =
                        new Cell(targetCell.getCol(), targetCell.getRow(), CellType.EMPTY);
                Message.printMessage("It was a WUMPUS on %s. The Number of arrows left %d.",
                        targetCell, heroCell.getArrows());
            }
            if (targetCell.getType().equals(CellType.WALL)) {
                Message.printMessage("It was a WALL. The arrow has fallen on the %s field. The Number of arrows left %d.",
                        targetCell, heroCell.getArrows());
            }
        } else if (heroCell.getArrows() <= 0) {
            Message.printMessage("Unfortunately, the hero has no more arrows.%n");
        }
    }

    private static Cell shootEndCell(GameState gameState, Direction direction) {
        boolean hit = false;
        Cell targetCell = gameState.getCurrentBoard().getHeroCell();
        int col = targetCell.getCol();
        int row = targetCell.getRow();
        int arrowStep = 1;
        if (direction.equals(Direction.NORTH)) {
            while ((row - arrowStep) >= 0 && !hit) {
                targetCell = gameState.getCurrentBoard().getCell(col, row - arrowStep);
                if (!targetCell.getType().equals(CellType.WALL) && !targetCell.getType().equals(CellType.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        if (direction.equals(Direction.SOUTH)) {
            while ((row + arrowStep) <= gameState.getCurrentBoard().getRowSize() && !hit) {
                targetCell = gameState.getCurrentBoard().getCell(col, row + arrowStep);
                if (!targetCell.getType().equals(CellType.WALL) && !targetCell.getType().equals(CellType.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        if (direction.equals(Direction.WEST)) {
            while ((col - arrowStep) >= 0 && !hit) {
                targetCell = gameState.getCurrentBoard().getCell(col - arrowStep, row);
                if (!targetCell.getType().equals(CellType.WALL) && !targetCell.getType().equals(CellType.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        if (direction.equals(Direction.EAST)) {
            while ((col + arrowStep) <= gameState.getCurrentBoard().getRowSize() && !hit) {
                targetCell = gameState.getCurrentBoard().getCell(col + arrowStep, row);
                if (!targetCell.getType().equals(CellType.WALL) && !targetCell.getType().equals(CellType.WUMPUS)) {
                    arrowStep++;
                } else {
                    hit = true;
                }
            }
        }
        return targetCell;
    }
}
