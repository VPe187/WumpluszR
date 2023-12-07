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
    static final int WUMPUSES_EASY = 1;
    static final int WUMPUSES_MEDIUM = 2;
    static final int WUMPUSES_HARD = 3;
    @XmlAttribute
    private int colSize;
    @XmlAttribute
    private int rowSize;
    private Cell[][] cells;
    private Cell[][] startCells;
    private Hero hero;

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

    public void reset() {
        cells = deepCopy(startCells);
        hero.reset(cells, getWumpusCountByWorldSize(colSize));
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

    /**
     * Give arrow and wumpus number depends on board size.
     *
     * @param worldSize as int
     * @return wumpus and arrow count
     */
    public int getWumpusCountByWorldSize(int worldSize) {
        if (worldSize <= 8) {
            return WUMPUSES_EASY;
        }
        if (worldSize <= 14) {
            return WUMPUSES_MEDIUM;
        } else {
            return WUMPUSES_HARD;
        }
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
