package hu.nye.progtech.wumpus.persistence.xml;

import java.util.List;
import java.util.Objects;

import hu.nye.progtech.wumpus.model.Cell;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Xml Cell Row.
 */
public class XmlCellRow {
    private List<Cell> cells;

    public XmlCellRow() {
    }

    public XmlCellRow(List<Cell> cells) {
        this.cells = cells;
    }

    @XmlElement(name = "cells")
    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XmlCellRow xmlCellRow = (XmlCellRow) o;
        return cells.equals(xmlCellRow.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }
}
