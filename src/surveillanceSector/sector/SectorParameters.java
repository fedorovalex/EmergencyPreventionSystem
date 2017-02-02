package surveillanceSector.sector;

public class SectorParameters {

    private final int rowCount;
    private final int columnCount;
    private final double fillFactor;

    public SectorParameters(int rowCount, int columnCount, double fillFactor) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.fillFactor = fillFactor;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public double getFillFactor() {
        return fillFactor;
    }
}
