package surveillanceSector;

import java.util.*;

public class Observer {

    private SurveillanceSector sector;

    public Observer(SurveillanceSector sector) {

        this.sector = sector;
    }

    public Map<RiskGroup, Integer> determineRiskGroups() {

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
                SectorsCage cage = sector.getCage(rowNumber, columnNumber);
                if (cage != null && !cage.isChecked() && cage.isBusy()) {
                    personsCountInGroups.add(calculateNeighborsCount(cage));
                }
            }
        }
        return personsCountInGroups;
    }

    private int calculateNeighborsCount(SectorsCage firstCage) {

        int peoplesCount = 0;
        Deque<SectorsCage> neighbors = new ArrayDeque<>();
        neighbors.offer(firstCage);
        SectorsCage cage;
        while ((cage = neighbors.poll()) != null) {
            peoplesCount++;
            cage.setChecked(true);
            for (SectorsCage cageNext: getNeighbors(cage)) {
                neighbors.offer(cageNext);
            }
        }
        return peoplesCount;
    }

    private List<SectorsCage> getNeighbors(SectorsCage cage) {
        List<SectorsCage> neighbors = new ArrayList<>();
        neighbors.add(sector.getCage(cage.getRowNumber(), cage.getColumnNumber() - 1));
        neighbors.add(sector.getCage(cage.getRowNumber(), cage.getColumnNumber() + 1));
        neighbors.add(sector.getCage(cage.getRowNumber() - 1, cage.getColumnNumber()));
        neighbors.add(sector.getCage(cage.getRowNumber() + 1, cage.getColumnNumber()));
        for (int i = 0; i <  neighbors.size(); i++) {
            cage = neighbors.get(i);
            if (cage == null || !cage.isBusy() || cage.isChecked()) {
                neighbors.remove(i);
                i--;
            }
        }
        return neighbors;
    }
}
