package surveillancesector.sector;

import surveillancesector.sector.sectorsCell.ConditionCell;
import surveillancesector.sector.sectorsCell.Coordinate;

public class ConverterSector {

    public String convertToString(Sector sector) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t ");
        for (int columnNumber = 1; columnNumber <= sector.getColumnCount(); columnNumber++) {
            stringBuilder.append(columnNumber);
            stringBuilder.append("\t ");
        }
        for (int y = 0; y < sector.getRowCount(); y++) {
            stringBuilder.append("\n");
            stringBuilder.append(y + 1);
            stringBuilder.append("\t");
            for (int x = 0; x < sector.getColumnCount(); x++) {
                if (sector.getCell(new Coordinate(x, y)) == ConditionCell.BUSY) {
                    stringBuilder.append("|X|\t");
                } else {
                    stringBuilder.append(" - \t");
                }
            }
        }
        return stringBuilder.toString();
    }
}
