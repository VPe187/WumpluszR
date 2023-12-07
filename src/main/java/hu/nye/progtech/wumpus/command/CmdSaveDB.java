package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.ui.Message;

public class CmdSaveDB implements Command{
    private static final String COMMAND = "d";
    private final GameState gameState;
    private final GameSavesRepository gameSavesRepository;

    public CmdSaveDB(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameSavesRepository = gameSavesRepository;
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameSavesRepository.save(gameState.getCurrentPlayer().getNickName(), gameState.getCurrentBoard());
        Message.printMessage("Current game state saved.");
    }
}
