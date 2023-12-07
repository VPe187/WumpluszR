package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;
import hu.nye.progtech.wumpus.model.Hero;
import hu.nye.progtech.wumpus.ui.Message;

/**
 * Indicate game movement.
 */
public class GameStep {

    public GameStep() {
    }

    /**
     * Check the Hero can move its sight to next cell. If can, give a tartget cell, if can't, give null.
     *
     * @param gameState as {@link GameState}
     * @return Cell or Null
     */
    public Cell canHeroMove(GameState gameState) {
        Board board = gameState.getCurrentBoard();
        Hero hero = board.getHero();
        if (hero.getSight().equals(Direction.NORTH) && (hero.getCurrentCell().getRow() - 1) < 0) {
            return null;
        }
        if (hero.getSight().equals(Direction.SOUTH) && (hero.getCurrentCell().getRow() + 1) > (board.getRowSize() - 1)) {
            return null;
        }
        if (hero.getSight().equals(Direction.WEST) && (hero.getCurrentCell().getCol() - 1) < 0) {
            return null;
        }
        if (hero.getSight().equals(Direction.EAST) && (hero.getCurrentCell().getCol() + 1) > (board.getColSize() - 1)) {
            return null;
        }
        Cell targetCell = getTargetCell(gameState);
        if (!targetCell.getType().equals(CellType.WALL)) {
            return targetCell;
        } else {
            return null;
        }
    }

    /**
     * Give back hero movement target cell if exsits.
     *
     * @param gameState as {@link GameState}
     * @return Cell which is a movement target.
     */
    public Cell getTargetCell(GameState gameState) {
        Board board = gameState.getCurrentBoard();
        Hero hero = gameState.getCurrentBoard().getHero();
        return switch (hero.getSight()) {
            case NORTH -> board.getCell(hero.getCurrentCell().getCol(), hero.getCurrentCell().getRow() - 1);
            case SOUTH -> board.getCell(hero.getCurrentCell().getCol(), hero.getCurrentCell().getRow() + 1);
            case WEST -> board.getCell(hero.getCurrentCell().getCol() - 1, hero.getCurrentCell().getRow());
            case EAST -> board.getCell(hero.getCurrentCell().getCol() + 1, hero.getCurrentCell().getRow());
        };
    }

    /**
     * Moving hero towards its sight to the next field.
     *
     * @param gameState as {@link GameState}
     * @param targetCell as {@link Cell}
     */
    public void move(GameState gameState, Cell targetCell) {
        Board board = gameState.getCurrentBoard();
        gameState.setSteps(gameState.getSteps() + 1);
        if (targetCell.getType().equals(CellType.WUMPUS)) {
            Message.printMessage("Your hero met a WUMPUS and died.");
            board.getHero().setDead(true);
            gameState.setRunning(false);
        } else if (targetCell.getType().equals(CellType.GOLD)) {
            Message.printMessage("Your hero pick up the gold.");
            board.getHero().setHasGold(true);
        } else if (targetCell.getType().equals(CellType.PIT)) {
            Message.printMessage("Your hero has fallen into the pit and lost 1 arrow.");
            board.getHero().loseArrow();
        } else {
            Message.printMessage("The hero has just moved to " + targetCell + " field.");
            if (board.getHero().checkGoal()) {
                Message.printMessage("You got the gold and you got out. You win by " + gameState.getSteps() + " steps.");
                gameState.setRunning(false);
            }
        }
        moveHeroTo(gameState, board.getHero(), targetCell);
        Message.printMessage("Target: " + targetCell);
    }

    /**
     * Move Hero to next target cell.
     *
     * @param hero as {@link Hero}
     * @param targetCell as {@link Cell}
     */
    public void moveHeroTo(GameState gameState, Hero hero, Cell targetCell) {
        int heroCol = hero.getCurrentCell().getCol();
        int heroRow = hero.getCurrentCell().getRow();
        int targetCol = targetCell.getCol();
        int targetRow = targetCell.getRow();
        gameState.getCurrentBoard().setOneCell(heroCol, heroRow, new Cell(heroCol, heroRow, CellType.EMPTY));
        hero.getCurrentCell().setCol(targetCol);
        hero.getCurrentCell().setRow(targetRow);
        gameState.getCurrentBoard().setOneCell(targetCol, targetRow, hero.getCurrentCell());
    }

    /**
     * Shoot arrow from Hero position to first collision.
     *
     * @param gameState as {@link GameState}
     */
    public void shoot(GameState gameState) {
        Board board = gameState.getCurrentBoard();
        Hero hero = board.getHero();
        if (hero.getArrows() > 0) {
            hero.loseArrow();
            Cell targetCell = shootEndCell(gameState, hero.getSight());
            if (targetCell.getType().equals(CellType.WUMPUS)) {
                board.getCells()[targetCell.getCol()][targetCell.getRow()] =
                        new Cell(targetCell.getCol(), targetCell.getRow(), CellType.EMPTY);
                Message.printMessage("It was a WUMPUS on %s. The Number of arrows left %d.",
                        targetCell, hero.getArrows());
            }
            if (targetCell.getType().equals(CellType.WALL)) {
                Message.printMessage("It was a WALL. The arrow has fallen on the %s field. The Number of arrows left %d.",
                        targetCell, hero.getArrows());
            }
        } else if (hero.getArrows() <= 0) {
            Message.printMessage("Unfortunately, the hero has no more arrows.%n");
        }
    }

    private Cell shootEndCell(GameState gameState, Direction direction) {
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
