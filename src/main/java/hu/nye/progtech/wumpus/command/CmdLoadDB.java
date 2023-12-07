package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.ui.Message;

/**
 * Command load form DB.
 */
public class CmdLoadDB implements Command {
    private static final String COMMAND = "b";
    private final GameState gameState;
    private final GameSavesRepository gameSavesRepository;

    public CmdLoadDB(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameSavesRepository = gameSavesRepository;
        this.gameState = gameState;
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
