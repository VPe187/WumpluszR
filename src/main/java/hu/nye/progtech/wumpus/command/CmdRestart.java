package hu.nye.progtech.wumpus.command;

import hu.nye.progtech.wumpus.game.GameState;

public class CmdRestart implements Command{
    private static final String COMMAND = "t";
    private final GameState gameState;

    public CmdRestart(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean validateCommand(String input) {
        return COMMAND.equalsIgnoreCase(input);
    }

    @Override
    public void process(String input) {

    }
}
