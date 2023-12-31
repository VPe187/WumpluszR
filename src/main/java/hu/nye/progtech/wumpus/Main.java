package hu.nye.progtech.wumpus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.board.BoardRaw;
import hu.nye.progtech.wumpus.board.BufferedBoardReader;
import hu.nye.progtech.wumpus.command.CmdEdit;
import hu.nye.progtech.wumpus.command.CmdLoad;
import hu.nye.progtech.wumpus.command.CmdLoadDB;
import hu.nye.progtech.wumpus.command.CmdMove;
import hu.nye.progtech.wumpus.command.CmdQuit;
import hu.nye.progtech.wumpus.command.CmdRestart;
import hu.nye.progtech.wumpus.command.CmdRotateLeft;
import hu.nye.progtech.wumpus.command.CmdRotateRight;
import hu.nye.progtech.wumpus.command.CmdSave;
import hu.nye.progtech.wumpus.command.CmdSaveDB;
import hu.nye.progtech.wumpus.command.CmdShoot;
import hu.nye.progtech.wumpus.command.Command;
import hu.nye.progtech.wumpus.exception.BoardParsingException;
import hu.nye.progtech.wumpus.game.GameController;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.game.GameStep;
import hu.nye.progtech.wumpus.input.InputHandler;
import hu.nye.progtech.wumpus.input.InputReader;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.input.MenuItem;
import hu.nye.progtech.wumpus.model.PlayerVO;
import hu.nye.progtech.wumpus.persistence.configuration.RepositoryConfiguration;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.ui.ConsolRenderer;

/**
 *  NYE Progtech Assigment - Wumplusz Refactored.
 */
public class Main {
    /**
     * Application entry point.
     */
    public static void main(String[] args) throws BoardParsingException {

        System.out.println("Kérem a felhasználó rövid nevét: ");
        Scanner scanner = new Scanner(System.in);
        String userNick = scanner.nextLine();
        if (userNick.equalsIgnoreCase("")) {
            userNick = "Unnamed";
        }
        PlayerVO playerVO = PlayerVO.builder().withNickName(userNick).build();
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
        Menu editMenu = createEditMenu(gameBoard.getColSize());
        GameState gameState = new GameState(gameBoard, playerVO, mainMenu);
        GameStep gameStep = new GameStep();
        List<Command> commands = createCommands(gameState, gameStep, editMenu);
        GameController wumpus = new GameController(gameState,
                new InputReader(new BufferedReader(new InputStreamReader(System.in))), new InputHandler(commands));
        ConsolRenderer.render(gameState, false);
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
        MenuItem menuItemSaveDB = new MenuItem("Save to database", "d");
        MenuItem menuItemLoadDB = new MenuItem("Load from database", "b");
        MenuItem menuItemQuit = new MenuItem("Quit", "q");
        mainMenu.addItem(menuItemMove);
        mainMenu.addItem(menuItemRotateLeft);
        mainMenu.addItem(menuItemRotateRight);
        mainMenu.addItem(menuItemShoot);
        mainMenu.addItem(menuItemRestart);
        mainMenu.addItem(menuItemEdit);
        mainMenu.addItem(menuItemSave);
        mainMenu.addItem(menuItemLoad);
        mainMenu.addItem(menuItemSaveDB);
        mainMenu.addItem(menuItemLoadDB);
        mainMenu.addItem(menuItemQuit);
        return mainMenu;
    }

    private static Menu createEditMenu(int boardSize) {
        Menu editMenu = new Menu(boardSize * 3 + boardSize + 1);
        MenuItem editMenuItemLoad = new MenuItem("Load from database", "b");
        MenuItem editMenuItemSave = new MenuItem("Save to database", "d");
        MenuItem editMenuItemAddWumpus = new MenuItem("Add Wumpus [colrow]", "u");
        MenuItem editMenuItemAddPit = new MenuItem("Add Pit [colrow]", "p");
        MenuItem editMenuItemQuit = new MenuItem("Return main menu", "q");
        editMenu.addItem(editMenuItemLoad);
        editMenu.addItem(editMenuItemSave);
        editMenu.addItem(editMenuItemAddWumpus);
        editMenu.addItem(editMenuItemAddPit);
        editMenu.addItem(editMenuItemQuit);
        return editMenu;
    }

    private static List<Command> createCommands(GameState gameState, GameStep gameStep, Menu editMenu) {
        RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
        GameSavesRepository storeBinRepository = repositoryConfiguration.createBinGameSavesRepository();
        GameSavesRepository storeDBRepository = repositoryConfiguration.createJdbcGameSavesRepository();
        GameSavesRepository storeXmlRepository = repositoryConfiguration.createXmlGameSavesRepository();
        return List.of(
                new CmdMove(gameState, gameStep),
                new CmdRotateLeft(gameState),
                new CmdRotateRight(gameState),
                new CmdShoot(gameState, gameStep),
                new CmdRestart(gameState),
                new CmdEdit(gameState, editMenu),
                new CmdSave(storeXmlRepository, gameState),
                new CmdLoad(storeXmlRepository, gameState),
                new CmdSaveDB(storeDBRepository, gameState),
                new CmdLoadDB(storeDBRepository, gameState),
                new CmdQuit(gameState)
        );
    }
}