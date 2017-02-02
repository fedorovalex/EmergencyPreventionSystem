package surveillanceSector.supervision;

import surveillanceSector.sector.Sector;
import surveillanceSector.sector.sectorsCell.SectorsCell;

public class SectorAuditLog {

    private boolean[][] auditLog;

    public SectorAuditLog(Sector sector) {

        auditLog = new boolean[sector.getRowCount()][sector.getColumnCount()];

        for (int rowNumber = 0; rowNumber < sector.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < sector.getColumnCount(); columnNumber++) {
                auditLog[rowNumber][columnNumber] = false;
            }
        }
    }

    public boolean isChecked(SectorsCell cell) {

        return isEntryIntoRange(cell) && auditLog[cell.getRowNumber()][cell.getColumnNumber()];
    }

    public void setCheck(SectorsCell cell) {

        if (isEntryIntoRange(cell)){
            auditLog[cell.getRowNumber()][cell.getColumnNumber()] = true;
        }
    }

    private boolean isEntryIntoRange(SectorsCell cell) {

        return
                cell != null &&
                cell.getRowNumber() >= 0 &&
                cell.getRowNumber() < auditLog.length &&
                cell.getColumnNumber() >= 0 &&
                cell.getColumnNumber() < auditLog[0].length;
    }
}
