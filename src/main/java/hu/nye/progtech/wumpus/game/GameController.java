package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.input.InputHandler;
import hu.nye.progtech.wumpus.input.InputReader;
import hu.nye.progtech.wumpus.ui.ConsolRenderer;

/**
 * Main game loop class.
 */
public class GameController {
    private final GameState gameState;
    private final InputReader inputReader;
    private final InputHandler inputHandler;
    private final GameStep gameStep;

    public GameController(GameState gameState, GameStep gameStep, InputReader inputReader, InputHandler inputHandler) {
        this.gameState = gameState;
        this.gameStep = gameStep;
        this.inputReader = inputReader;
        this.inputHandler = inputHandler;
    }

    /**
     * Start game loop, read user inputs, process commands.
     */
    public void start() {
        while (gameState.isRunning()) {
            String input = inputReader.readInput();
            inputHandler.handleInput(input);
            ConsolRenderer.render(gameState, !gameState.isRunning());
        }
        System.exit(0);
    }
}
