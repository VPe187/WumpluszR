package hu.nye.progtech.wumpus.persistence.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hu.nye.progtech.wumpus.board.Board;

/**
 * Save gamestate to binary file.
 */
public class BinaryGameSavesRepository implements GameSavesRepository {
    private String filePath = "state.bin";

    public BinaryGameSavesRepository() {
    }

    @Override
    public void save(String username, Board currentBoard) {
        ObjectOutputStream oos = null;
        long start = System.currentTimeMillis();
        try {
            File f = new File(username + "_" + filePath);
            FileOutputStream fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(currentBoard);
            System.out.println("File saved:" + f.getAbsolutePath() + " " + (System.currentTimeMillis() - start) + "ms");
        } catch (Exception ecx) {
            ecx.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Board load(String username) {
        Board board = null;
        ObjectInputStream ois = null;
        try {
            File f = new File(username + "_" + filePath);
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            board = (Board) ois.readObject();
        } catch (Exception ecx) {
            ecx.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return board;

    }
}
