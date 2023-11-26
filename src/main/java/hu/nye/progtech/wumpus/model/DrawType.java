package hu.nye.progtech.wumpus.model;

/**
 * This enum class conatins all ascii character element to render game board.
 */
public enum DrawType {
    LeftUp(0x250F),
    Horizontal(0x2501),
    Vertical(0x2503),
    RightUpper(0x2513),
    VerticalLeft(0x2523),
    VerticalRight(0x252B),
    LeftDown(0x2517),
    RightDown(0x251B),
    CrossUp(0x2533),
    Cross(0x254B),
    CrossDown(0x253B),
    Space(0x20),
    North(0x2193);
    private final int value;

    DrawType(final int value) {
        this.value = value;
    }

    public char getValue() {
        return (char) value;
    }
}
