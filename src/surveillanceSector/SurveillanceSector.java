package surveillanceSector;


import java.util.*;

public class SurveillanceSector {

    private SectorsCage[][] sector;

    public SurveillanceSector(SectorParameters sectorParameters) {
        this(
                sectorParameters.getRowCount(),
                sectorParameters.getColumnCount(),
                sectorParameters.getFillFactor()
        );
    }

    public SurveillanceSector(int rowCount, int columnCount, double fillFactor) {
        if (rowCount < 1) {
            rowCount = 1;
        }
        if (columnCount < 1) {
            columnCount = 1;
        }
        if (fillFactor < 0 || fillFactor > 1) {
            fillFactor = Math.abs(fillFactor - (int) fillFactor);
        }
        sector = new SectorsCage[rowCount][columnCount];

        for (int rowNumber = 0; rowNumber < rowCount; rowNumber++) {
            for (int columnNumber = 0; columnNumber < columnCount; columnNumber++) {
                sector[rowNumber][columnNumber] = new SectorsCage(rowNumber, columnNumber, isBusy(fillFactor));
            }
        }
    }

    public String collectToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t ");
        for (int columnNumber = 1; columnNumber <= getColumnCount(); columnNumber++) {
            stringBuilder.append(columnNumber);
            stringBuilder.append("\t ");
        }
        for (int rowNumber = 0; rowNumber < getRowCount(); rowNumber++) {
            stringBuilder.append("\n");
            stringBuilder.append(rowNumber + 1);
            stringBuilder.append("\t");
            for (int columnNumber = 0; columnNumber < getColumnCount(); columnNumber++) {
                if (sector[rowNumber][columnNumber].isBusy()) {
                    stringBuilder.append("|X|\t");
                } else {
                    stringBuilder.append(" - \t");
                }
            }
        }
        return stringBuilder.toString();
    }

    public SectorsCage getCage(int rowNumber, int columnNumber) {
        if (rowNumber < 0 || rowNumber >= getRowCount() || columnNumber < 0 || columnNumber >= getColumnCount()) {
            return null;
        }
        return sector[rowNumber][columnNumber];
    }

    public int getRowCount() {
        return sector.length;
    }

    public int getColumnCount() {
        return sector[0].length;
    }

    private boolean isBusy(double fillFactor) {
        Random random = new Random();
        return random.nextDouble() < fillFactor;
    }
}
