package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;
import hu.nye.progtech.wumpus.input.Menu;

/**
 * Edit command.
 */
public class CmdEdit implements Command {
    private static final String COMMAND = "e";
    private final GameState gameState;
    private final Menu menu;

    public CmdEdit(GameState gameState, Menu menu) {
        this.gameState = gameState;
        this.menu = menu;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {
        gameState.setCurrentMenu(menu);
    }
}
