package hu.nye.progtech.wumpus.persistence.xml;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;

/**
 * XmlBoard class.
 */
public class XmlBoard {
    private List<XmlCellRow> rows;

    public XmlBoard() {
    }

    public XmlBoard(List<XmlCellRow> rows) {
        this.rows = rows;
    }

    @XmlElement(name = "row")
    public List<XmlCellRow> getRows() {
        return rows;
    }

    public void setRows(List<XmlCellRow> rows) {
        this.rows = rows;
    }
}
