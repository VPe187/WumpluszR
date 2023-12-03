package hu.nye.progtech.wumpus.game;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.input.Menu;
import hu.nye.progtech.wumpus.model.Player;

/**
 * This class store actual user and played board
 */
public class GameState {
    private final Board currentBoard;
    private final Player currentPlayer;
    private final Menu currentMenu;

    public GameState(Board board, Player player, Menu menu) {
        this.currentBoard = board;
        this.currentMenu = menu;
        this.currentPlayer = player;
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
}
