package hu.nye.progtech.wumpus.model;

/**
 * Specified {@linkCell} type: Hero.
 */
public class CellHero extends Cell {
    private int arrows;
    private int hasGold;
    private int startCol;
    private int startRow;

    public CellHero() {
        super(CellType.HERO);
    }
}
