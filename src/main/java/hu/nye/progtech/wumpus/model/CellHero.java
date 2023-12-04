package hu.nye.progtech.wumpus.model;

/**
 * Specified {@linkCell} type: Hero.
 */
public class CellHero extends Cell {
    private int arrows;
    private boolean hasGold;
    private boolean dead;
    private Direction sight;
    private final int startCol;
    private final int startRow;


    public CellHero(int col, int row, int arrows, boolean hasGold, int startCol, int startRow, Direction sight) {
        super(col, row, CellType.HERO);
        this.arrows = arrows;
        this.hasGold = hasGold;
        this.startCol = startCol;
        this.startRow = startRow;
        this.sight = sight;
        dead = false;
    }

    public int getArrows() {
        return arrows;
    }

    public boolean getHasGold() {
        return hasGold;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Direction getSight() {
        return sight;
    }

    public boolean isDead() {
        return dead;
    }

    public void rotateLeft() {
        sight = sight.left();
    }

    public void rotateRight() {
        sight = sight.right();
    }

    public void loseArrow() {
        if(arrows > 0) {
            arrows--;
        }
    }

    public boolean checkGoal() {
        return getCol() == startCol && getRow() == startRow && getHasGold();
    }
}
