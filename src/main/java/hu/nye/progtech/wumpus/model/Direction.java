package hu.nye.progtech.wumpus.model;

/**
 * Direction types enum.
 */
public enum Direction {
    NORTH("N"), WEST("W"), SOUTH("S"), EAST("E");
    private final String sign;

    /**
     * Directions enum.
     *
     * @param sign as String
     */
    Direction(final String sign) {
        this.sign = sign;
    }

    /**
     * Give back HeroSight by value.
     *
     * @param sign as String
     * @return {@link Direction}
     */
    public static Direction getByValue(String sign) {
        for (Direction value : values()) {
            if (value.sign.equals(sign)) {
                return value;
            }
        }
        return null;
    }

    public Direction left() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    public Direction right() {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }
}
