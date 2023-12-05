package hu.nye.progtech.wumpus.persistence.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.persistence.jdbc.GameSavesRepository;

/**
 * JDBC Game Save Repository.
 */
public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {
    static final String INSERT_STATEMENT = "INSERT INTO game_saves (username, map, fixed) VALUES (?, ?, ?);";
    static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE username = ?;";
    static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE username = ?;";
    private Connection connection;
    private BoardParser boardParser;

    public JdbcGameSavesRepository(Connection connection, BoardParser boardParser) {
        this.connection = connection;
        this.boardParser = boardParser;
    }

    @Override
    public void save(String username, Board currentBoard) {

    }

    @Override
    public Board load(String username) {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private void deleteCurrentlyStoredSave(String username) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATEMENT)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }

    private void insertNewSave(String username, Board currentBoard) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }
}
