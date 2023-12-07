package hu.nye.progtech.wumpus.persistence.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.ui.Message;

/**
 * Save gamestate to binary file.
 */
public class BinaryGameSavesRepository implements GameSavesRepository {
    private final String filePath = "state.bin";

    public BinaryGameSavesRepository() {
    }

    @Override
    public void save(String username, Board currentBoard) {
        ObjectOutputStream oos;
        long start = System.currentTimeMillis();
        try {
            File file = new File(username + "_" + filePath);
            FileOutputStream fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(currentBoard);
            Message.printMessage("File saved:" + file.getAbsolutePath() + " (" + (System.currentTimeMillis() - start) + "ms)");
        } catch (Exception e) {
            Message.printMessage(e.getMessage());
        }
    }

    @Override
    public Board load(String username) {
        Board board = null;
        ObjectInputStream ois;
        try {
            File file = new File(username + "_" + filePath);
            if (file.exists() && !file.isDirectory()) {
                FileInputStream fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                board = (Board) ois.readObject();
            }
        } catch (Exception e) {
            Message.printMessage(e.getMessage());
        }
        return board;
    }
}
