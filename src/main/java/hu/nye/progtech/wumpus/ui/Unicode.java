package hu.nye.progtech.wumpus.ui;

/**
 * This enum class conatins all ascii character element to render game board.
 */
public enum Unicode {
    LEFT_UP(0x250F),
    HORIZONTAL(0x2501),
    VERTICAL(0x2503),
    RIGHT_UPPER(0x2513),
    VERTICAL_LEFT(0x2523),
    VERTICAL_RIGHT(0x252B),
    LEFT_DOWN(0x2517),
    RIGHT_DOWN(0x251B),
    CROSS_UP(0x2533),
    CROSS(0x254B),
    CROSS_DOWN(0x253B),
    SPACE(0x20),
    NORTH(0x2191),
    SOUTH(0x2193),
    WEST(0x2190),
    EAST(0x2192);
    private final int value;

    Unicode(final int value) {
        this.value = value;
    }

    public char getValue() {
        return (char) value;
    }


    @Override
    public String toString() {
        return Character.toString((char) this.value);
    }
}
