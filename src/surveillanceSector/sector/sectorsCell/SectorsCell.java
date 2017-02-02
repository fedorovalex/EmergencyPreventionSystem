package surveillanceSector.sector.sectorsCell;


public class SectorsCell {

    private final ConditionCell condition;
    private final int rowNumber;
    private final int columnNumber;

    public SectorsCell(int rowNumber, int columnNumber, ConditionCell condition) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.condition = condition;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public ConditionCell getCondition() {
        return condition;
    }
}