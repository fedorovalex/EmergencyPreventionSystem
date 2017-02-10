package surveillancesector.sector;

import surveillancesector.sector.sectorsCell.ConditionCell;
import surveillancesector.sector.sectorsCell.Coordinate;


public class Sector {

    private ConditionCell[][] sector;

    public Sector(int rowCount, int columnCount) {

        assert(rowCount > 0 && columnCount > 0);
        sector = new ConditionCell[rowCount][columnCount];
    }

    public ConditionCell getCell(Coordinate coordinate) {

        if (!isEntryIntoRange(coordinate)) {
            return ConditionCell.FREE;
        }
        return sector[coordinate.getY()][coordinate.getX()];
    }

    public void setCell(Coordinate coordinate, ConditionCell conditionCell) {

        if (isEntryIntoRange(coordinate)) {
            sector[coordinate.getY()][coordinate.getX()] = conditionCell;
        }
    }

    public int getRowCount() {
        return sector.length;
    }

    public int getColumnCount() {
        return sector[0].length;
    }

    private boolean isEntryIntoRange(Coordinate coordinate) {

        return coordinate.getY() < getRowCount() && coordinate.getX() < getColumnCount();
    }
}
