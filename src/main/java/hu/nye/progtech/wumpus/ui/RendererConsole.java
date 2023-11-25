package hu.nye.progtech.wumpus.ui;

import hu.nye.progtech.wumpus.board.Board;

public class RendererConsole {
    private static final int asciiLeftUp = 0x250F;
    private static final int asciiHorizontal = 0x2501;
    private static final int asciiVertical = 0x2503;
    private static final int asciiRightUpper = 0x2513;
    private static final int asciiVerticalLeft = 0x2523;
    private static final int asciiVerticalRight = 0x252B;
    private static final int asciiLeftDown = 0x2517;
    private static final int asciiRightDown = 0x251B;
    private static final int asciiCrossUp = 0x2533;
    private static final int asciiCross = 0x254B;
    private static final int asciiCrossDown = 0x253B;

    public RendererConsole() {

    }

    public static void renderBoard(Board board) {
        for (int i = 0; i <= board.getSize(); i++) {
            for (int j = 0; j <= board.getSize(); j++) {
                if (i == 0) {
                    if (j == 0) {
                        echo(asciiLeftUp);
                        echo(asciiHorizontal);
                        echo(asciiHorizontal);
                    } else if (j < board.getSize()) {
                        echo(asciiCrossUp);
                        echo(asciiHorizontal);
                        echo(asciiHorizontal);
                    } else if (j == board.getSize()) {
                        echo(asciiRightUpper);
                    }
                } else if (i < board.getSize()) {
                    if (j == 0) {
                        echo(asciiVerticalLeft);
                        echo(asciiHorizontal);
                        echo(asciiHorizontal);
                    } else if (j < board.getSize()) {
                        echo(asciiCross);
                        echo(asciiHorizontal);
                        echo(asciiHorizontal);
                    } else if (j == board.getSize()) {
                        echo(asciiVerticalRight);
                    }
                } else if (i == board.getSize()) {
                    if (j == 0) {
                        echo(asciiLeftDown);
                        echo(asciiHorizontal);
                        echo(asciiHorizontal);
                    } else if (j < board.getSize()) {
                        echo(asciiCrossDown);
                        echo(asciiHorizontal);
                        echo(asciiHorizontal);
                    } else if (j == board.getSize()) {
                        echo(asciiRightDown);
                    }
                }
            }
            System.out.println();
        }
    }

    private static void echo (int asciiCode) {
        System.out.print((char) asciiCode);
    }
}
