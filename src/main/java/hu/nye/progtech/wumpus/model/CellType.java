package hu.nye.progtech.wumpus.model;

/**
 * Cell types enum.
 */
public enum CellType {
    WALL("W", Color.COLOR_PURPLE),
    WUMPUS("U", Color.COLOR_RED),
    PIT("P", Color.COLOR_BLUE),
    EMPTY("_", Color.COLOR_BLACK),
    GOLD("G", Color.COLOR_YELLOW),
    HERO("H", Color.COLOR_GREEN),
    DEAD_HERO("✝", Color.COLOR_RED);
    private final String sign;
    private final Color color;

    /** Cell type enums.
     *
     * @param sign as String
     */
    CellType(final String sign, final Color color) {
        this.sign = sign;
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.sign;
    }

}
