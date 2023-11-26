package hu.nye.progtech.wumpus.model;

/**
 * This class represent one field of board.
 */
public class Cell {
    public static CellBuilder builder() {
        return new CellBuilder();
    }

    private final CellType type;

    public Cell(CellType value) {
        this.type = value;
    }

    public CellType getType() {
        return type;
    }

    /**
     * Builder for {@link Cell}.
     */
    public static final class CellBuilder {
        private CellType type;

        public static CellBuilder builder() {
            return new CellBuilder();
        }

        /**
         * Cell builder with value.
         */
        public CellBuilder withType(CellType value) {
            type = value;
            return this;
        }

        public Cell build() {
            return new Cell(type);
        }

        public Cell buildHero() {
            return new CellHero();
        }
    }
}
