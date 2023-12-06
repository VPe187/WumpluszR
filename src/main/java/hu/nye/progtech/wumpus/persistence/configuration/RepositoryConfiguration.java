package hu.nye.progtech.wumpus.persistence.configuration;

import java.sql.SQLException;

import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.JdbcGameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.XmlGameSavesRepository;
import jakarta.xml.bind.JAXBException;

/**
 * Spring Java configuration class for persistence layer specific Spring Beans.
 */
public class RepositoryConfiguration {

    public GameSavesRepository createJdbcGameSavesRepository() throws SQLException, JAXBException {
        return new JdbcGameSavesRepository();
    }

    public GameSavesRepository createXmlGameSavesRepository() throws JAXBException {
        return new XmlGameSavesRepository();
    }
}
