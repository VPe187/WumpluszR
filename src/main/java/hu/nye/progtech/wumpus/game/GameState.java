package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.model.Player;

/**
 * This class store actual user and played board.
 */
public class GameState {
    private final Board currentBoard;
    private final Player currentPlayer;
    private final Menu currentMenu;
    private boolean running;
    private int steps;

    public GameState(Board board, Player player, Menu menu) {
        this.currentBoard = board;
        this.currentMenu = menu;
        this.currentPlayer = player;
        running = true;
        steps = 0;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public boolean isRunning() {
        return running;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
