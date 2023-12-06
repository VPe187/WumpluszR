package hu.nye.progtech.wumpus.persistence.repository;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.progtech.wumpus.board.Board;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * JDBC Game Save Repository.
 */
public class JdbcGameSavesRepository implements GameSavesRepository {
    static final String CREATE_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS game_saves (username VARCHAR(50), boardxml VARCHAR(4000))";
    static final String INSERT_STATEMENT = "INSERT INTO game_saves (username, boardxml) VALUES (?, ?);";
    static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE username = ?;";
    static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE username = ?;";
    static final String TRUNCATE_STATEMENT = "TRUNCATE TABLE game_saves;";
    private Marshaller marshaller = null;
    private Unmarshaller unmarshaller = null;

    public JdbcGameSavesRepository() {
        try {
            createTable();
            JAXBContext ctx = JAXBContext.newInstance(Board.class);
            marshaller = ctx.createMarshaller();
            unmarshaller = ctx.createUnmarshaller();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:/boardh2;AUTO_SERVER=TRUE", "sa", "");
    }

    @Override
    public void save(String username, Board currentBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        StringWriter writer = null;
        try {
            delete(username);
            writer = new StringWriter();
            marshaller.marshal(currentBoard, writer);
            writer.close();
            conn = createConnection();
            pstmt = conn.prepareStatement(INSERT_STATEMENT);
            String boardXml = writer.toString();
            pstmt.setString(1, username);
            pstmt.setString(2, boardXml);
            pstmt.executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                assert pstmt != null;
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Board load(String username) {
        Board board = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        StringReader reader = null;
        try {
            conn = createConnection();
            pstmt = conn.prepareStatement(SELECT_STATEMENT);
            pstmt.setString(1, username);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                String boardXml = resultSet.getString("boardxml");
                reader = new StringReader(boardXml);
                board = (Board) unmarshaller.unmarshal(reader);
                reader.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return board;
    }

    private void delete(String username) {
        Connection conn = null;
        Statement pstmt = null;
        try {
            conn = createConnection();
            pstmt = conn.createStatement();
            int rows = pstmt.executeUpdate("DELETE FROM game_saves WHERE username='" + username + "';");
            System.out.println(rows + " sor törölve.");
            pstmt.executeUpdate(TRUNCATE_STATEMENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createTable() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = createConnection();
            pstmt = conn.prepareStatement(CREATE_TABLE_STATEMENT);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                conn.close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
