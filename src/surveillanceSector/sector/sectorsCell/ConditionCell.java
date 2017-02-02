package surveillanceSector.sector.sectorsCell;

import java.util.Random;

public enum ConditionCell {
    BUSY,
    FREELY;

    public static ConditionCell getConditionCell(double fillFactor) {

        if (fillFactor < 0 || fillFactor > 1) {
            fillFactor = Math.abs(fillFactor - (int) fillFactor);
        }

        Random random = new Random();
        if (random.nextDouble() < fillFactor) {
            return BUSY;
        }
        return FREELY;
    }
}