package hu.nye.progtech.wumpus.persistence.xml;

import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Xml Board Value Object.
 */
@XmlRootElement(name = "board")
@XmlType(propOrder = {"board"})
public class XmlBoardVO {
    private int colSize;
    private int rowSize;
    private XmlBoard xmlBoard;

    public XmlBoardVO() {
    }

    public XmlBoardVO(int colSize, int rowSize, XmlBoard xmlBoard) {
        this.colSize = colSize;
        this.rowSize = rowSize;
        this.xmlBoard = xmlBoard;
    }

    @XmlAttribute
    public int getColSize() {
        return colSize;
    }

    @XmlAttribute
    public int getRowSize() {
        return rowSize;
    }

    @XmlAttribute
    public XmlBoard getXmlBoard() {
        return xmlBoard;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setXmlBoard(XmlBoardVO xmlBoardVO) {
        this.xmlBoard = xmlBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XmlBoardVO xmlBoardVO = (XmlBoardVO) o;
        return colSize == xmlBoardVO.colSize && rowSize == xmlBoardVO.rowSize && xmlBoardVO.equals(xmlBoardVO.xmlBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colSize, rowSize, xmlBoard);
    }
}
