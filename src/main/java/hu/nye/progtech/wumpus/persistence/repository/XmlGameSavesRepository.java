package hu.nye.progtech.wumpus.persistence.repository;

import java.io.File;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.ui.Message;
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
    private final String filePath = "state.xml";

    public XmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    public XmlGameSavesRepository() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Board.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            unmarshaller = ctx.createUnmarshaller();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void save(String username, Board currentBoard) {
        try {
            File file = new File(username + "_" + filePath);
            long start = System.currentTimeMillis();
            marshaller.marshal(currentBoard, file);

            Message.printMessage("File saved:" + file.getAbsolutePath() + " (" + (System.currentTimeMillis() - start) + "ms)");
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to save XML", e);
        }
    }

    @Override
    public Board load(String username) {
        Board board = null;
        try {
            File file = new File(username + "_" + filePath);
            if (file.exists() && !file.isDirectory()) {
                board = (Board) unmarshaller.unmarshal(file);
            } else {
                Message.printMessage("Game state file not exists.");
                return null;
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to load XML", e);
        }
        return board;
    }
}
