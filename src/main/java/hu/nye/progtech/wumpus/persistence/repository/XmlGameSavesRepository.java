package hu.nye.progtech.wumpus.persistence.repository;

import java.io.File;
import java.io.FileOutputStream;

import hu.nye.progtech.wumpus.board.Board;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * XML Game Save Repository.
 */
public class XmlGameSavesRepository implements GameSavesRepository {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private String filePath = "state.xml";

    public XmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    public XmlGameSavesRepository() {
        try {
            long start = System.currentTimeMillis();
            JAXBContext ctx = JAXBContext.newInstance(Board.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            unmarshaller = ctx.createUnmarshaller();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public void save(String username, Board currentBoard) {
        try {
            File f = new File(username + "_" + filePath);
            long start = System.currentTimeMillis();
            marshaller.marshal(currentBoard, f);
            System.out.println("File saved:" + f.getAbsolutePath() + " " + (System.currentTimeMillis() - start) + "ms");
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to save XML", e);
        }
    }

    @Override
    public Board load(String username) {
        Board board = null;
        try {
            board = (Board) unmarshaller.unmarshal(new File(username + "_" + filePath));
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to load XML", e);
        }
        return board;
    }
}
