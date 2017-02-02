package surveillanceSector.sector;

import surveillanceSector.sector.sectorsCell.ConditionCell;
import surveillanceSector.sector.sectorsCell.SectorsCell;


public class Creator {

    public static Sector createSector(SectorParameters sectorParameters) {

        return createSector(
                sectorParameters.getRowCount(),
                sectorParameters.getColumnCount(),
                sectorParameters.getFillFactor()
        );
    }

    public static Sector createSector(int rowCount, int columnCount, double fillFactor) {

        Sector sector = new Sector(rowCount, columnCount);

        for (int rowNumber = 0; rowNumber < sector.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < sector.getColumnCount(); columnNumber++) {
                sector.setCell(
                        new SectorsCell(
                                rowNumber,
                                columnNumber,
                                ConditionCell.getConditionCell(fillFactor)
                        )
                );
            }
        }

        return sector;
    }

    public static Sector createSector(int rowCount, int columnCount, ConditionCell[][] cells) {

        Sector sector = new Sector(rowCount, columnCount);

        for (int rowNumber = 0; rowNumber < sector.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < sector.getColumnCount(); columnNumber++) {
                sector.setCell(
                        new SectorsCell(
                                rowNumber,
                                columnNumber,
                                getCondition(rowNumber, columnNumber, cells)
                        )
                );
            }
        }

        return sector;
    }

    private static ConditionCell getCondition(int rowNumber, int columnNumber, ConditionCell[][] cells) {

        ConditionCell conditionCell;
        try {
            conditionCell = cells[rowNumber][columnNumber];
        } catch (ArrayIndexOutOfBoundsException e) {
            conditionCell = ConditionCell.FREELY;
        }
        return conditionCell;
    }
}
