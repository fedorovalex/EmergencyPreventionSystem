package surveillanceSector.sector;

import surveillanceSector.sector.sectorsCell.SectorsCell;


public class Sector {

    private SectorsCell[][] sector;

    public Sector(int rowCount, int columnCount) {

        if (rowCount < 1) {
            rowCount = 1;
        }
        if (columnCount < 1) {
            columnCount = 1;
        }

        sector = new SectorsCell[rowCount][columnCount];
    }

    public SectorsCell getCell(int rowNumber, int columnNumber) {

        if (!isEntryIntoRange(rowNumber, columnNumber)) {
            return null;
        }
        return sector[rowNumber][columnNumber];
    }

    public void setCell(SectorsCell cell) {

        if (cell != null && isEntryIntoRange(cell.getRowNumber(), cell.getColumnNumber())) {
            sector[cell.getRowNumber()][cell.getColumnNumber()] = cell;
        }
    }

    public int getRowCount() {
        return sector.length;
    }

    public int getColumnCount() {
        return sector[0].length;
    }

    private boolean isEntryIntoRange(int rowNumber, int columnNumber) {

        return rowNumber >= 0 && rowNumber < getRowCount() && columnNumber >= 0 && columnNumber < getColumnCount();
    }
}
