package surveillanceSector;


public class SectorsCage {

    private boolean checked;
    private final boolean busy;
    private final int rowNumber;
    private final int columnNumber;

    public SectorsCage(int rowNumber, int columnNumber, boolean busy) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.busy = busy;
        this.checked = false;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public boolean isBusy() {
        return busy;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}