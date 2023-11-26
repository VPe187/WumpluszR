package hu.nye.progtech.wumpus.model;

/**
 * This enum class contains board item colors.
 */
public enum Color {
    COLOR_RESET("\u001B[0m"),
    COLOR_BLACK("\u001B[30m"),
    COLOR_RED("\u001B[31m"),
    COLOR_GREEN("\u001B[32m"),
    COLOR_YELLOW("\u001B[33m"),
    COLOR_BLUE("\u001B[34m"),
    COLOR_PURPLE("\u001B[35m"),
    COLOR_CYAN("\u001B[36m"),
    COLOR_WHITE("\u001B[37m");
    private final String value;

    Color(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
