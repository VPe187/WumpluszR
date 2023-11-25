package hu.nye.progtech.wumpus.model;

/**
 * Cell types enum.
 */
public enum CellType {
    WALL("W"), WUMPUS("U"), PIT("P"), EMPTY("_"), GOLD("G"), HERO("H");
    private final String sign;

    /** Cell type enums.
     *
     * @param sign as String
     */
    CellType(final String sign) {
        this.sign = sign;
    }

    /** Cell element by value.
     *
     * @param sign as String
     *
     * @return {@link CellType}
     */
    public static CellType getByValue(String sign) {
        for (CellType value : values()) {
            if (value.sign.equals(sign)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.sign;
    }

}
