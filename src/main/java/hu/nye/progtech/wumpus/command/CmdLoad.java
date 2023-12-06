package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.ui.Message;

/**
 * Load command.
 */
public class CmdLoad implements Command {
    private static final String COMMAND = "o";
    private final GameState gameState;
    private final GameSavesRepository gameSavesRepository;

    public CmdLoad(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameState = gameState;
        this.gameSavesRepository = gameSavesRepository;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        Board loadedBoard = gameSavesRepository.load(gameState.getCurrentPlayer().getNickName());
        if (loadedBoard != null) {
            gameState.setCurrentBoard(loadedBoard);
            Message.printMessage("Game state loaded.");
        }
    }
}
