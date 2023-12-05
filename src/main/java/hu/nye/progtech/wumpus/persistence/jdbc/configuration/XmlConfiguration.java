package hu.nye.progtech.wumpus.persistence.jdbc.configuration;

import hu.nye.progtech.wumpus.persistence.xml.BoardToXmlBoardConverter;
import hu.nye.progtech.wumpus.persistence.xml.XmlBoardToBoardConverter;
import hu.nye.progtech.wumpus.persistence.xml.XmlBoardVO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for XML related components.
 */
@Configuration
public class XmlConfiguration {

    @Bean
    Marshaller marshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlBoardVO.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        return marshaller;
    }

    @Bean
    Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlBoardVO.class);

        return jaxbContext.createUnmarshaller();
    }

    @Bean
    BoardToXmlBoardConverter boardToXmlBoardConverter() {
        return new BoardToXmlBoardConverter();
    }

    @Bean
    XmlBoardToBoardConverter xmlBoardToBoardConverter() {
        return new XmlBoardToBoardConverter();
    }

}
