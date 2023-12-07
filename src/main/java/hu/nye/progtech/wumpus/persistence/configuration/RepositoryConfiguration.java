package hu.nye.progtech.wumpus.persistence.configuration;

import hu.nye.progtech.wumpus.persistence.repository.BinaryGameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.JdbcGameSavesRepository;
import hu.nye.progtech.wumpus.persistence.repository.XmlGameSavesRepository;

/**
 * Spring Java configuration class for persistence layer specific Spring Beans.
 */
public class RepositoryConfiguration {

    public GameSavesRepository createJdbcGameSavesRepository() {
        return new JdbcGameSavesRepository();
    }

    public GameSavesRepository createXmlGameSavesRepository() {
        return new XmlGameSavesRepository();
    }

    public GameSavesRepository createBinGameSavesRepository() {
        return new BinaryGameSavesRepository();
    }
}
