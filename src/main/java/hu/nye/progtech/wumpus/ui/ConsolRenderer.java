package hu.nye.progtech.wumpus.ui;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.input.MenuItem;
import hu.nye.progtech.wumpus.model.Cell;
import hu.nye.progtech.wumpus.model.CellType;
import hu.nye.progtech.wumpus.model.Color;
import hu.nye.progtech.wumpus.model.Hero;
import hu.nye.progtech.wumpus.model.PlayerVO;
import hu.nye.progtech.wumpus.util.BoardUtil;

/**
 * This class render {@link Board} to console.
 */
public class ConsolRenderer {

    private static final String menuHeaderText = "MENU";
    private static final String menuHotkeySeparator = "()";
    private static final String menuPadding = " ";

    /**
     * This is a render method.
     *
     * @param gameState as {@link GameState}
     */
    public static void render(GameState gameState) {
        renderBoard(gameState.getCurrentBoard());
        menu(gameState.getCurrentMenu(), gameState.getCurrentPlayer());
    }

    private static void renderBoard(Board board) {
        Cell[][] cells = board.getCells();
        int boardSize = board.getColSize();
        printString(Unicode.SPACE.toString(), Color.COLOR_BLUE);
        firstRow(boardSize);
        for (int i = 0; i < boardSize; i++) {
            printString(String.valueOf(i + 1), Color.COLOR_BLUE);
            internalRow(boardSize, cells, i, board.getHero());
            if (i == boardSize - 1) {
                lastRow(boardSize);
            } else {
                separatorRow(boardSize);
            }
        }

    }

    private static void menu(Menu menu, PlayerVO playerVO) {
        menuHeader(menu.getWidth(), playerVO);
        for (MenuItem menuItem : menu.getMenuList()) {
            print(Unicode.SPACE, Color.COLOR_BLACK);
            print(Unicode.SPACE, Color.COLOR_BLACK);
            printString(menuItem.getLabel() + menuPadding, Color.COLOR_WHITE);
            printRepeat(menu.getWidth() - menuItem.getLabel().length() - menuHotkeySeparator.length() -
                            menuPadding.length() * 2 - menuItem.getHotKey().length(), ".", Color.COLOR_WHITE);
            printString(menuPadding + menuHotkeySeparator.charAt(0) + menuItem.getHotKey() +
                   menuHotkeySeparator.charAt(1), Color.COLOR_GREEN);
            printLF();
        }
        menuFooter(menu.getWidth());
        printLF();
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        printString("Your choiche: ", Color.COLOR_CYAN);
    }

    private static void menuHeader(int menuWidth, PlayerVO playerVO) {
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        printRepeat(menuWidth, Unicode.HORIZONTAL.toString(), Color.COLOR_WHITE);
        printLF();
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        printString(Unicode.HORIZONTAL + Unicode.HORIZONTAL.toString() + " " + menuHeaderText + " ", Color.COLOR_WHITE);
        printString(playerVO.getNickName() + " ", Color.COLOR_YELLOW);
        printRepeat(menuWidth - menuHeaderText.length() - playerVO.getNickName().length() - 5,
                Unicode.HORIZONTAL.toString(), Color.COLOR_WHITE);
        printLF();
    }

    private static void menuFooter(int menuWidth) {
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        printRepeat(menuWidth, Unicode.HORIZONTAL.toString(), Color.COLOR_WHITE);
    }

    private static void firstRow(int boardSize) {
        print(Unicode.SPACE, Color.COLOR_BLACK);
        for (int i = 0; i < boardSize; i++) {
            print(Unicode.SPACE, Color.COLOR_BLACK);
            print(Unicode.SPACE, Color.COLOR_BLACK);
            printString(BoardUtil.letterFromInteger(i), Color.COLOR_BLUE);
            print(Unicode.SPACE, Color.COLOR_BLACK);
        }
        printLF();
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.LEFT_UP, Color.COLOR_WHITE);
        for (int i = 1; i < boardSize; i++) {
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.CROSS_UP, Color.COLOR_WHITE);
        }
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.RIGHT_UPPER, Color.COLOR_WHITE);
        printLF();
    }

    private static void separatorRow(int boardSize) {
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.VERTICAL_LEFT, Color.COLOR_WHITE);
        for (int i = 1; i < boardSize; i++) {
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.CROSS, Color.COLOR_WHITE);
        }
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.VERTICAL_RIGHT, Color.COLOR_WHITE);
        printLF();
    }

    private static void internalRow(int boardSize, Cell[][] cells, int row, Hero hero) {
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.VERTICAL, Color.COLOR_WHITE);
        for (int i = 0; i < boardSize; i++) {
            printCell(cells[i][row], hero);
        }
        printLF();
    }

    private static void lastRow(int boardSize) {
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.SPACE, Color.COLOR_BLACK);
        print(Unicode.LEFT_DOWN, Color.COLOR_WHITE);
        for (int i = 1; i < boardSize; i++) {
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
            print(Unicode.CROSS_DOWN, Color.COLOR_WHITE);
        }
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.HORIZONTAL, Color.COLOR_WHITE);
        print(Unicode.RIGHT_DOWN, Color.COLOR_WHITE);
        printLF();
    }

    private static void print(Unicode asciiCode, Color color) {
        System.out.print(Color.COLOR_RESET);
        System.out.print(color);
        System.out.print(asciiCode.getValue());
    }

    private static void printCell(Cell cell, Hero hero) {
        print(Unicode.SPACE, Color.COLOR_WHITE);
        printCellValue(cell, hero);
        print(Unicode.SPACE, Color.COLOR_WHITE);
        print(Unicode.VERTICAL, Color.COLOR_WHITE);
    }

    private static void printCellValue(Cell cell, Hero hero) {
        System.out.print(Color.COLOR_RESET);
        System.out.print(cell.getType().getColor());
        if (cell.getType().equals(CellType.HERO)) {
            if (hero.isDead()) {
                printString("✝", Color.COLOR_RED);
            } else if (hero.checkGoal()) {
                printString("✓", Color.COLOR_GREEN);
            } else {
                switch (hero.getSight()) {
                    case NORTH: {
                        print(Unicode.NORTH, hero.getHasGold() ? Color.COLOR_YELLOW : Color.COLOR_GREEN);
                        break;
                    }
                    case SOUTH: {
                        print(Unicode.SOUTH, hero.getHasGold() ? Color.COLOR_YELLOW : Color.COLOR_GREEN);
                        break;
                    }
                    case WEST: {
                        print(Unicode.WEST, hero.getHasGold() ? Color.COLOR_YELLOW : Color.COLOR_GREEN);
                        break;
                    }
                    case EAST: {
                        print(Unicode.EAST, hero.getHasGold() ? Color.COLOR_YELLOW : Color.COLOR_GREEN);
                        break;
                    }
                    default:
                        System.out.print("H");
                }
            }
        } else {
            System.out.print(cell.getType());
        }
    }

    private static void printLF() {
        System.out.println();
    }

    private static void printString(String text, Color color) {
        System.out.print(Color.COLOR_RESET);
        System.out.print(color);
        System.out.print(text);
    }

    private static void printRepeat(int width, String text, Color color) {
        System.out.print(Color.COLOR_RESET);
        System.out.print(color);
        for (int i = 0; i < width; i++) {
            printString(text, color);
        }
    }
}
