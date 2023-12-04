package hu.nye.progtech.wumpus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.board.BufferedBoardReader;
import hu.nye.progtech.wumpus.command.CmdMove;
import hu.nye.progtech.wumpus.command.CmdQuit;
import hu.nye.progtech.wumpus.command.CmdRotate;
import hu.nye.progtech.wumpus.command.Command;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.Game;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.input.InputHandler;
import hu.nye.progtech.wumpus.input.InputReader;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.input.MenuItem;
import hu.nye.progtech.wumpus.model.Player;

/**
 *  NYE Progtech Assigment - Wumplusz Refactored.
 */
public class Main {
    /**
     * Application entry point.
     */
    public static void main(String[] args) throws BoardParsingException {
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
        Menu mainMenu = createMainMenu(gameBoard.getColSize());
        Player player = new Player("VPe187");
        GameState gameState = new GameState(gameBoard, player, mainMenu);
        List<Command> commands = createCommands(gameState);
        Game wumpus = new Game(gameState, new InputReader(new BufferedReader(new InputStreamReader(System.in))),
                new InputHandler(commands));
        wumpus.start();
    }

    private static Menu createMainMenu(int boardSize) {
        Menu mainMenu = new Menu(boardSize * 3 + boardSize + 1);
        MenuItem menuItemMove = new MenuItem("Move", "m");
        MenuItem menuItemRotate = new MenuItem("Rotate", "r");
        MenuItem menuItemShoot = new MenuItem("Shoot", "s");
        MenuItem menuItemRestart = new MenuItem("Restart", "t");
        MenuItem menuItemEdit = new MenuItem("Edit", "e");
        MenuItem menuItemSave = new MenuItem("Save", "v");
        MenuItem menuItemQuit = new MenuItem("Quit", "q");
        mainMenu.addItem(menuItemMove);
        mainMenu.addItem(menuItemRotate);
        mainMenu.addItem(menuItemShoot);
        mainMenu.addItem(menuItemRestart);
        mainMenu.addItem(menuItemEdit);
        mainMenu.addItem(menuItemSave);
        mainMenu.addItem(menuItemQuit);
        return mainMenu;
    }

    private static List<Command> createCommands(GameState gameState) {
        return List.of(
                new CmdRotate(gameState),
                new CmdMove(gameState),
                new CmdQuit(gameState)
        );
    }
}