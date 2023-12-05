package hu.nye.progtech.wumpus.persistence.jdbc.repository;

import java.io.File;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.board.PersistableBoard;
import hu.nye.progtech.wumpus.persistence.jdbc.GameSavesRepository;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * XML Game Save Repository.
 */
public class XmlGameSavesRepository implements GameSavesRepository {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public void save(String username, Board currentBoard) {
        try {
            PersistableBoard persistableBoard =
                    new PersistableBoard(currentBoard.getColSize(), currentBoard.getRowSize(), currentBoard.getCells());
            marshaller.marshal(persistableBoard, new File("state.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to save XML", e);
        }
    }

    @Override
    public Board load(String username) {
        try {
            PersistableBoard persistableBoard = (PersistableBoard) unmarshaller.unmarshal(new File("state.xml"));

            return new Board(persistableBoard.getColSize(), persistableBoard.getRowSize(), persistableBoard.getCells());
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to load XML", e);
        }
    }
}
