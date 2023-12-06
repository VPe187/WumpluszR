package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;

/**
 * Load command.
 */
public class CmdLoad implements Command {
    private static final String COMMAND = "v";
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
        gameSavesRepository.save(gameState.getCurrentPlayer().getNickName(), gameState.getCurrentBoard());
        System.out.println("Current game state saved.");
    }
}
