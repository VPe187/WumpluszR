package hu.nye.progtech.wumpus.board;

import java.io.Serializable;

import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Direction;
import hu.nye.progtech.wumpus.model.Hero;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This class is the board of game.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "cells",
        "startCells",
        "hero"
})
@XmlRootElement(name = "Board")
public class Board implements Serializable {
    @XmlAttribute
    private int colSize;
    @XmlAttribute
    private int rowSize;
    private Cell[][] cells;
    private Cell[][] startCells;
    private Hero hero = null;

    public Board() {
    }

    public Board(int colSize, int rowSize, Cell[][] cells) {
        this.colSize = colSize;
        this.rowSize = rowSize;
        this.cells = cells;
        this.startCells = deepCopy(cells);
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Cell[][] getStartCells() {
        return startCells;
    }

    public void setStartCells(Cell[][] startCells) {
        this.startCells = startCells;
    }

    public void setOneCell(int col, int row, Cell cell) {
        cells[col][row] = cell;
    }

    public Cell getCell(int col, int row) {
        return cells[col][row];
    }

    /**
     * Select {@link Cell} from Board cells.
     *
     * @return {@link Cell}
     */
    public Cell getHeroCell() {
        return this.hero.getCurrentCell();
    }

    /**
     * Check the Hero can move its sight direction.
     * Return target cell as {@link Cell} depends on Hero can move to.
     */
    public Cell canHeroMove() {
        if (hero.getSight().equals(Direction.NORTH) && (hero.getCurrentCell().getRow() - 1) < 0) {
            return null;
        }
        if (hero.getSight().equals(Direction.SOUTH) && (hero.getCurrentCell().getRow() + 1) > (getRowSize() - 1)) {
            return null;
        }
        if (hero.getSight().equals(Direction.WEST) && (hero.getCurrentCell().getCol() - 1) < 0) {
            return null;
        }
        if (hero.getSight().equals(Direction.EAST) && (hero.getCurrentCell().getCol() + 1) > (getColSize() - 1)) {
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
        return switch (hero.getSight()) {
            case NORTH -> getCell(hero.getCurrentCell().getCol(), hero.getCurrentCell().getRow() - 1);
            case SOUTH -> getCell(hero.getCurrentCell().getCol(), hero.getCurrentCell().getRow() + 1);
            case WEST -> getCell(hero.getCurrentCell().getCol() - 1, hero.getCurrentCell().getRow());
            case EAST -> getCell(hero.getCurrentCell().getCol() + 1, hero.getCurrentCell().getRow());
        };
    }

    /**
     * Move Hero to next target cell.
     *
     * @param heroCell as {@link Hero}
     * @param targetCell as {@link Cell}
     */
    public void moveHeroTo(Hero heroCell, Cell targetCell) {
        int heroCol = hero.getCurrentCell().getCol();
        int heroRow = hero.getCurrentCell().getRow();
        int targetCol = targetCell.getCol();
        int targetRow = targetCell.getRow();
        setOneCell(heroCol, heroRow, new Cell(heroCol, heroRow, CellType.EMPTY));
        hero.getCurrentCell().setCol(targetCol);
        hero.getCurrentCell().setRow(targetRow);
        setOneCell(targetCol, targetRow, hero.getCurrentCell());
    }

    public void reset() {
        cells = startCells;
    }

    private Cell[][] deepCopy(Cell[][] sourceCells) {
        Cell[][] newCells = new Cell[sourceCells.length][sourceCells.length];
        for (int i = 0; i < sourceCells.length; i++) {
            for (int j = 0; j < sourceCells.length; j++) {
                newCells[j][i] = new Cell(sourceCells[j][i].getCol(), sourceCells[j][i].getRow(), sourceCells[j][i].getType());
            }
        }
        return newCells;
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
