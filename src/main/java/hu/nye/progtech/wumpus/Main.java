package hu.nye.progtech.wumpus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.board.BufferedBoardReader;
import hu.nye.progtech.wumpus.command.CmdLoad;
import hu.nye.progtech.wumpus.command.CmdMove;
import hu.nye.progtech.wumpus.command.CmdQuit;
import hu.nye.progtech.wumpus.command.CmdRestart;
import hu.nye.progtech.wumpus.command.CmdRotateLeft;
import hu.nye.progtech.wumpus.command.CmdRotateRight;
import hu.nye.progtech.wumpus.command.CmdSave;
import hu.nye.progtech.wumpus.command.CmdShoot;
import hu.nye.progtech.wumpus.command.Command;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.GameController;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.input.InputHandler;
import hu.nye.progtech.wumpus.input.InputReader;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.input.MenuItem;
import hu.nye.progtech.wumpus.model.PlayerVO;
import hu.nye.progtech.wumpus.persistence.repository.BinaryGameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.XmlGameSavesRepository;
import hu.nye.progtech.wumpus.ui.ConsolRenderer;
import jakarta.xml.bind.JAXBException;

/**
 *  NYE Progtech Assigment - Wumplusz Refactored.
 */
public class Main {
    /**
     * Application entry point.
     */
    public static void main(String[] args) throws BoardParsingException, JAXBException, IOException {
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
        PlayerVO playerVO = PlayerVO.builder().withNickName("VPe187").build();
        GameState gameState = new GameState(gameBoard, playerVO, mainMenu);
        List<Command> commands = createCommands(gameState);
        GameController wumpus = new GameController(gameState, new InputReader(new BufferedReader(new InputStreamReader(System.in))),
                new InputHandler(commands));
        ConsolRenderer.render(gameState);
        wumpus.start();
    }

    private static Menu createMainMenu(int boardSize) {
        Menu mainMenu = new Menu(boardSize * 3 + boardSize + 1);
        MenuItem menuItemMove = new MenuItem("Move", "m");
        MenuItem menuItemRotateLeft = new MenuItem("Rotate left", "l");
        MenuItem menuItemRotateRight = new MenuItem("Rotate right", "r");
        MenuItem menuItemShoot = new MenuItem("Shoot", "s");
        MenuItem menuItemRestart = new MenuItem("Restart", "t");
        MenuItem menuItemEdit = new MenuItem("Edit", "e");
        MenuItem menuItemSave = new MenuItem("Save game state", "v");
        MenuItem menuItemLoad = new MenuItem("Load game state", "o");
        MenuItem menuItemQuit = new MenuItem("Quit", "q");
        mainMenu.addItem(menuItemMove);
        mainMenu.addItem(menuItemRotateLeft);
        mainMenu.addItem(menuItemRotateRight);
        mainMenu.addItem(menuItemShoot);
        mainMenu.addItem(menuItemRestart);
        mainMenu.addItem(menuItemEdit);
        mainMenu.addItem(menuItemSave);
        mainMenu.addItem(menuItemLoad);
        mainMenu.addItem(menuItemQuit);
        return mainMenu;
    }

    private static List<Command> createCommands(GameState gameState) {
        //GameSavesRepository storeRepository = new BinaryGameSavesRepository();
        GameSavesRepository storeRepository = new XmlGameSavesRepository();
        return List.of(
                new CmdMove(gameState),
                new CmdRotateLeft(gameState),
                new CmdRotateRight(gameState),
                new CmdShoot(gameState),
                new CmdRestart(gameState),
                new CmdSave(storeRepository, gameState),
                new CmdLoad(storeRepository, gameState),
                new CmdQuit(gameState)
        );
    }
}