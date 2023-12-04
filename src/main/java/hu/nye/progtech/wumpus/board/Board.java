package hu.nye.progtech.wumpus.board;

import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellHero;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;

/**
 * This class is the board of game.
 */
public class Board {
    private final int colSize;
    private final int rowSize;
    private final Cell[][] cells;

    public Board(int colSize, int rowSize, Cell[][] cells) {
        this.colSize = colSize;
        this.rowSize = rowSize;
        this.cells = cells;
    }

    public void setOneCell(int col, int row, Cell cell) {
        cells[col][row] = cell;
    }

    public int getColSize() {
        return colSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int col, int row) {
        return cells[col][row];
    }

    /**
     * Select {@link Cell} from Board cells.
     *
     * @return {@link Cell}
     */
    public CellHero getHeroCell() {
        for (int i = 0; i < getRowSize(); i++) {
            for (int j = 0; j < getColSize(); j++) {
                if (cells[j][i] instanceof CellHero) {
                    return (CellHero) cells[j][i];
                }
            }
        }
        return null;
    }

    /**
     * Check the Hero can move its sight direction.
     * Return target cell as {@link Cell} depends on Hero can move to.
     */
    public Cell canHeroMove() {
        CellHero heroCell = getHeroCell();
        if (heroCell.getSight().equals(Direction.NORTH) && (heroCell.getRow() - 1) < 0) {
            return null;
        }
        if (heroCell.getSight().equals(Direction.SOUTH) && (heroCell.getRow() + 1) > (getRowSize() - 1)) {
            return null;
        }
        if (heroCell.getSight().equals(Direction.WEST) && (heroCell.getCol() - 1) < 0) {
            return null;
        }
        if (heroCell.getSight().equals(Direction.EAST) && (heroCell.getCol() + 1) > (getColSize() - 1)) {
            return null;
        }
        Cell targetCell = getTargetCell();
        if (!targetCell.getType().equals(CellType.WALL)) {
            return targetCell;
        } else {
            return null;
        }
    }

    private Cell getTargetCell() {
        CellHero heroCell = getHeroCell();
        return switch (heroCell.getSight()) {
            case NORTH -> getCell(heroCell.getCol(), heroCell.getRow() - 1);
            case SOUTH -> getCell(heroCell.getCol(), heroCell.getRow() + 1);
            case WEST -> getCell(heroCell.getCol() - 1, heroCell.getRow());
            case EAST -> getCell(heroCell.getCol() + 1, heroCell.getRow());
        };
    }

    /**
     * Move Hero to next target cell.
     *
     * @param heroCell as {@link CellHero}
     * @param targetCell as {@link Cell}
     */
    public void moveHeroTo(CellHero heroCell, Cell targetCell) {
        int heroCol = heroCell.getCol();
        int heroRow = heroCell.getRow();
        int targetCol = targetCell.getCol();
        int targetRow = targetCell.getRow();
        setOneCell(heroCol, heroRow, new Cell(heroCol, heroRow, CellType.EMPTY));
        heroCell.setCol(targetCol);
        heroCell.setRow(targetRow);
        setOneCell(targetCol, targetRow, heroCell);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < colSize; j++) {
                stringBuilder.append(cells[j][i].getType());
            }
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
