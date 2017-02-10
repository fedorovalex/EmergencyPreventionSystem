package surveillancesector.supervision;

import surveillancesector.sector.sectorsCell.Coordinate;

public class AccountingVisitSector {

    private boolean[][] accountingVisitedCells;

    public AccountingVisitSector(int rowCount, int columnCount) {

        accountingVisitedCells = new boolean[rowCount][columnCount];
    }

    public boolean isVisited(Coordinate coordinate) {

        return isEntryIntoRange(coordinate) && accountingVisitedCells[coordinate.getY()][coordinate.getX()];
    }

    public void toVisit(Coordinate coordinate) {

        if (isEntryIntoRange(coordinate)){
            accountingVisitedCells[coordinate.getY()][coordinate.getX()] = true;
        }
    }

    private boolean isEntryIntoRange(Coordinate coordinate) {

        return coordinate.getY() < accountingVisitedCells.length && coordinate.getX() < accountingVisitedCells[0].length;
    }
}
