package surveillanceSector.sector;

import surveillanceSector.sector.sectorsCell.ConditionCell;

public class ConverterSector {

    public static String convertToString(Sector sector) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t ");
        for (int columnNumber = 1; columnNumber <= sector.getColumnCount(); columnNumber++) {
            stringBuilder.append(columnNumber);
            stringBuilder.append("\t ");
        }
        for (int rowNumber = 0; rowNumber < sector.getRowCount(); rowNumber++) {
            stringBuilder.append("\n");
            stringBuilder.append(rowNumber + 1);
            stringBuilder.append("\t");
            for (int columnNumber = 0; columnNumber < sector.getColumnCount(); columnNumber++) {
                if (sector.getCell(rowNumber, columnNumber).getCondition() == ConditionCell.BUSY) {
                    stringBuilder.append("|X|\t");
                } else {
                    stringBuilder.append(" - \t");
                }
            }
        }
        return stringBuilder.toString();
    }
}
