package hu.nye.progtech.wumpus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.board.BufferedBoardReader;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.input.MenuItem;
import hu.nye.progtech.wumpus.model.Player;
import hu.nye.progtech.wumpus.ui.ConsolRenderer;

/**
 *  NYE Progtech Assigment - Wumplusz Refactored.
 */
public class Main {
    /**
     * Application entry point.
     */
    public static void main(String[] args) throws BoardParsingException {
        Player player = new Player("Petike");

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("wumpuszinput.txt");
        BoardRaw boardRaw = null;
        try {
            assert inputStream != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                BufferedBoardReader bufferedBoardReader = new BufferedBoardReader(bufferedReader);
                boardRaw = new BoardRaw(bufferedBoardReader.readBoard());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        assert boardRaw != null;
        BoardParser boardParser = new BoardParser(boardRaw);
        Board gameBoard = boardParser.parseRawBoard();

        MenuItem menuItemMove = new MenuItem("Move", "m");
        MenuItem menuItemRotate = new MenuItem("Rotate", "r");
        Menu mainMenu = new Menu();
        mainMenu.addItem(menuItemMove);
        mainMenu.addItem(menuItemRotate);

        GameState gameState = new GameState(gameBoard, player, mainMenu );
        ConsolRenderer.renderBoard(gameState.getCurrentBoard());
        ConsolRenderer.renderMenu(gameState.getCurrentMenu());
    }
}