package surveillanceSector.supervision;

import surveillanceSector.sector.sectorsCell.ConditionCell;
import surveillanceSector.sector.sectorsCell.SectorsCell;
import surveillanceSector.sector.Sector;

import java.util.*;
import java.util.stream.Stream;

public class Inspector {

    private Sector sector;
    private SectorAuditLog auditLog;

    public Inspector(Sector sector) {

        this.sector = sector;
    }

    public Map<RiskGroup, Integer> determineRiskGroups() {

        auditLog = new SectorAuditLog(sector);

        Map<RiskGroup, Integer> groupsCountByRisk = new HashMap<>();
        for (RiskGroup riskGroup: RiskGroup.values()) {
            groupsCountByRisk.put(riskGroup, 0);
        }

        List<Integer> personsCountInGroups = calculationPersonsCountInGroups();
        for (Integer count: personsCountInGroups) {
            RiskGroup riskGroup = RiskGroup.identifyGroup(count);
            int oldValue = groupsCountByRisk.get(riskGroup);
            groupsCountByRisk.put(riskGroup, ++oldValue);
        }
        return groupsCountByRisk;
    }

    private List<Integer> calculationPersonsCountInGroups() {

        List<Integer> personsCountInGroups = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < sector.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < sector.getColumnCount(); columnNumber++) {
                SectorsCell cell = sector.getCell(rowNumber, columnNumber);
                if (cell != null && !auditLog.isChecked(cell) && cell.getCondition() == ConditionCell.BUSY) {
                    personsCountInGroups.add(calculateNeighborsCount(cell));
                }
            }
        }
        return personsCountInGroups;
    }

    private int calculateNeighborsCount(SectorsCell firstCell) {

        int peoplesCount = 0;
        Deque<SectorsCell> neighbors = new ArrayDeque<>();
        auditLog.setCheck(firstCell);
        neighbors.offer(firstCell);
        SectorsCell cell;
        while ((cell = neighbors.poll()) != null) {
            peoplesCount++;

            getNeighbors(cell)
                    .filter(cellNext -> !auditLog.isChecked(cellNext))
                    .forEach(cellNext -> {
                        auditLog.setCheck(cellNext);
                        neighbors.offer(cellNext);
                    });
        }
        return peoplesCount;
    }

    private Stream<SectorsCell> getNeighbors(SectorsCell cell) {

        List<SectorsCell> neighbors = new ArrayList<>();
        neighbors.add(sector.getCell(cell.getRowNumber(), cell.getColumnNumber() - 1));
        neighbors.add(sector.getCell(cell.getRowNumber(), cell.getColumnNumber() + 1));
        neighbors.add(sector.getCell(cell.getRowNumber() - 1, cell.getColumnNumber()));
        neighbors.add(sector.getCell(cell.getRowNumber() + 1, cell.getColumnNumber()));

        return neighbors.stream()
                .filter(cellNext -> cellNext != null && cellNext.getCondition() == ConditionCell.BUSY);
    }
}