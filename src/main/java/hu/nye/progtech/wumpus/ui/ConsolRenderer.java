package hu.nye.progtech.wumpus.ui;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.Color;
import hu.nye.progtech.wumpus.model.DrawType;

/**
 * This class render {@link Board} to console.
 */
public class ConsolRenderer {
    /**
     * This is a render method.
     *
     * @param board as {@link Board}
     */
    public static void renderBoard(Board board) {
        Cell[][] cells = board.getCells();
        int boardSize = board.getSize();
        typeFirstRow(boardSize);
        for (int i = 0; i < boardSize; i++) {
            typeInternalRow(boardSize, cells, i);
            if (i == boardSize - 1) {
                tpyeLastRow(boardSize);
            } else {
                typeSeparatorRow(boardSize);
            }
        }

    }

    /**
     * Render a cell.
     *
     * @param cell as {@link Cell}
     */
    public static void typeCell(Cell cell) {
        echo(DrawType.Space);
        echoCell(cell);
        echo(DrawType.Space);
        echo(DrawType.Vertical);
    }

    private static void typeFirstRow(int boardSize) {
        echo(DrawType.LeftUp);
        for (int i = 1; i < boardSize; i++) {
            echo(DrawType.Horizontal);
            echo(DrawType.Horizontal);
            echo(DrawType.Horizontal);
            echo(DrawType.CrossUp);
        }
        echo(DrawType.Horizontal);
        echo(DrawType.Horizontal);
        echo(DrawType.Horizontal);
        echo(DrawType.RightUpper);
        echoLF();
    }

    private static void typeSeparatorRow(int boardSize) {
        echo(DrawType.VerticalLeft);
        for (int i = 1; i < boardSize; i++) {
            echo(DrawType.Horizontal);
            echo(DrawType.Horizontal);
            echo(DrawType.Horizontal);
            echo(DrawType.Cross);
        }
        echo(DrawType.Horizontal);
        echo(DrawType.Horizontal);
        echo(DrawType.Horizontal);
        echo(DrawType.VerticalRight);
        echoLF();
    }

    private static void typeInternalRow(int boardSize, Cell[][] cells, int row) {
        echo(DrawType.Vertical);
        for (int i = 0; i < boardSize; i++) {
            typeCell(cells[i][row]);
        }
        echoLF();
    }

    private static void tpyeLastRow(int boardSize) {
        echo(DrawType.LeftDown);
        for (int i = 1; i < boardSize; i++) {
            echo(DrawType.Horizontal);
            echo(DrawType.Horizontal);
            echo(DrawType.Horizontal);
            echo(DrawType.CrossDown);
        }
        echo(DrawType.Horizontal);
        echo(DrawType.Horizontal);
        echo(DrawType.Horizontal);
        echo(DrawType.RightDown);
        echoLF();
    }

    private static void echo(DrawType asciiCode) {
        System.out.print(Color.COLOR_RESET);
        System.out.print(Color.COLOR_WHITE);
        System.out.print(asciiCode.getValue());
    }

    private static void echoCell(Cell cell) {
        System.out.print(Color.COLOR_RESET);
        System.out.print(cell.getType().getColor());
        System.out.print(cell.getType());
    }

    private static void echoLF() {
        System.out.println();
    }
}
