package hu.nye.progtech.wumpus.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Specified {@linkCell} type: Hero.
 */
@XmlRootElement(name = "Hero")
@XmlType(name = "", propOrder = {
        "arrows",
        "hasGold",
        "dead",
        "sight",
        "startCol",
        "startRow",
        "currentCell"
})
public class Hero {
    private int arrows;
    private boolean hasGold;
    private boolean dead;
    private Direction sight;
    private Direction startSight;
    private int startCol;
    private int startRow;
    private Cell currentCell;

    public Hero() {
    }

    public Hero(Cell currentCell, int arrows, boolean hasGold, int startCol, int startRow, Direction sight) {
        this.currentCell = currentCell;
        this.arrows = arrows;
        this.hasGold = hasGold;
        this.startCol = startCol;
        this.startRow = startRow;
        this.sight = sight;
        this.startSight = sight;
        dead = false;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public boolean getHasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public boolean getDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Direction getSight() {
        return sight;
    }

    public void setSight(Direction sight) {
        this.sight = sight;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void rotateLeft() {
        sight = sight.left();
    }

    public void rotateRight() {
        sight = sight.right();
    }

    /**
     * Hero loose one arrow.
     */
    public void loseArrow() {
        if (arrows > 0) {
            arrows--;
        }
    }

    public boolean checkGoal() {
        return currentCell.getCol() == startCol && currentCell.getRow() == startRow && getHasGold();
    }

    public void reset(Cell[][] cells) {
        currentCell = cells[startCol][startRow];
        sight = startSight;
    }
}
