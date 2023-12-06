package hu.nye.progtech.wumpus.model;

import java.io.Serializable;

import hu.nye.progtech.wumpus.util.BoardUtil;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * This class represent one field of board.
 */
@XmlRootElement(name = "Cell")
public class Cell implements Serializable {

    private final CellType type;
    private int col;
    private int row;

    public Cell() {
        this.type = null;
    }

    public Cell(int col, int row, CellType value) {
        this.type = value;
        this.col = col;
        this.row = row;
    }

    @XmlAttribute
    public CellType getType() {
        return type;
    }

    @XmlAttribute
    public int getCol() {
        return col;
    }

    @XmlAttribute
    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return BoardUtil.letterFromInteger(col) + (row + 1);
    }
}
