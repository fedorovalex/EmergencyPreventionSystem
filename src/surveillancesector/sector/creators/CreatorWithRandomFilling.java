package surveillancesector.sector.creators;


import surveillancesector.sector.Sector;
import surveillancesector.sector.sectorsCell.ConditionCell;
import surveillancesector.sector.sectorsCell.Coordinate;

import java.util.Random;

public class CreatorWithRandomFilling {

    public Sector create(int rowCount, int columnCount, double fillFactor) {

        Sector sector = new Sector(rowCount, columnCount);

        if (fillFactor < 0 || fillFactor > 1) {
            fillFactor = Math.abs(fillFactor - (int) fillFactor);
        }

        for (int y = 0; y < sector.getRowCount(); y++) {
            for (int x = 0; x < sector.getColumnCount(); x++) {
                sector.setCell(new Coordinate(x, y), getConditionCell(fillFactor));
            }
        }

        return sector;
    }

    private ConditionCell getConditionCell(double fillFactor) {

        Random random = new Random();
        if (random.nextDouble() < fillFactor) {
            return ConditionCell.BUSY;
        }
        return ConditionCell.FREE;
    }
}
