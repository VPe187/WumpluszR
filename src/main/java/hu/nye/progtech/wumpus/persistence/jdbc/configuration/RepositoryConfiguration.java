package hu.nye.progtech.wumpus.persistence.jdbc.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.nye.progtech.wumpus.board.BoardParser;
import hu.nye.progtech.wumpus.persistence.jdbc.GameSavesRepository;
import hu.nye.progtech.wumpus.persistence.jdbc.repository.JdbcGameSavesRepository;
import hu.nye.progtech.wumpus.persistence.jdbc.repository.XmlGameSavesRepository;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Repository Configuration Class.
 */
@Configuration
public class RepositoryConfiguration {
    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    @Bean(destroyMethod = "close")
    GameSavesRepository jdbcGameSavesRepository(Connection connection, BoardParser boardParser) {
        return new JdbcGameSavesRepository(connection, boardParser);
    }

    @Primary
    @Bean
    GameSavesRepository xmlGameSavesRepository(Marshaller marshaller, Unmarshaller unmarshaller) {
        return new XmlGameSavesRepository(marshaller, unmarshaller);
    }
}
