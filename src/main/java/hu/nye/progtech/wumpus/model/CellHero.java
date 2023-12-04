package hu.nye.progtech.wumpus.model;

/**
 * Specified {@linkCell} type: Hero.
 */
public class CellHero extends Cell {
    private int arrows;
    private boolean hasGold;
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

    public Direction getSight() {
        return sight;
    }

    public void rotateLeft() {
        sight = sight.left();
    }
}
