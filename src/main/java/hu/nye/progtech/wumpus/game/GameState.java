package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.model.PlayerVO;

/**
 * This class store actual user and played board.
 */
public class GameState {
    private Board currentBoard;
    private final PlayerVO currentPlayerVO;
    private Menu currentMenu;
    private boolean running;
    private int steps;

    public GameState(Board board, PlayerVO playerVO, Menu menu) {
        this.currentBoard = board;
        this.currentMenu = menu;
        this.currentPlayerVO = playerVO;
        running = true;
        steps = 0;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public PlayerVO getCurrentPlayer() {
        return currentPlayerVO;
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

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
