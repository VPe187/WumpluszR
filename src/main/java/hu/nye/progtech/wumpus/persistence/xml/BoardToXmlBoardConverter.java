package hu.nye.progtech.wumpus.persistence.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.nye.progtech.wumpus.board.Board;
import hu.nye.progtech.wumpus.model.Cell;

/**
 * Board to Xml converter.
 */
public class BoardToXmlBoardConverter {
    /**
     * Converts the given {@link Board} object to {@link XmlBoardVO}.
     *
     * @param board the {@link Board} to convert
     * @return an {@link XmlBoardVO} object
     */
    public XmlBoardVO convert(Board board) {
        int colSize = board.getColSize();
        int rowSize = board.getRowSize();
        XmlBoard xmlBoard = getXmlBoard(board);
        return new XmlBoardVO(colSize, rowSize, xmlBoard);
    }

    private XmlBoard getXmlBoard(Board board) {
        Cell[][] cells = board.getCells();
        List<XmlCellRow> rows = new ArrayList<>();
        for (Cell[] cellRow : cells) {
            List<Cell> tmp = new ArrayList<>();
            Collections.addAll(tmp, cellRow);
            XmlCellRow xmlCellRow = new XmlCellRow(tmp);
            rows.add(xmlCellRow);
        }
        return new XmlBoard(rows);
    }
}
