package hu.nye.progtech.wumpus.persistence.jdbc;

import hu.nye.progtech.wumpus.board.Board;

/**
 * Game Save Repository Interface.
 */
public interface GameSavesRepository {
    void save(String username, Board currentBoard);

    Board load(String username);
}
