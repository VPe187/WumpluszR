package hu.nye.progtech.wumpus.persistence.xml;

import java.util.List;
import java.util.Objects;

import hu.nye.progtech.wumpus.model.Cell;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * XmlCell class.
 */
public class XmlCell {
    private List<Cell> cell;

    public XmlCell() {
    }

    public XmlCell(List<Cell> cell) {
        this.cell = cell;
    }

    @XmlElement(name = "cells")
    public List<Cell> getCell() {
        return cell;
    }

    public void setCell(List<Cell> cell) {
        this.cell = cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XmlCell xmlCells = (XmlCell) o;
        return cell.equals(xmlCells.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell);
    }
}
