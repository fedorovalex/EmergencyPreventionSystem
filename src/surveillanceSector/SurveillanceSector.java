package surveillanceSector;


import java.util.*;

public class SurveillanceSector {

    private SectorsCage[][] sector;

    public SurveillanceSector(int rowCount, int columnCount, double fillFactor) {
        if (rowCount < 1) {
            rowCount = 1;
        }
        if (columnCount < 1) {
            columnCount = 1;
        }
        //не лучле ли if?
        fillFactor = Math.abs(fillFactor - (int)fillFactor);
        sector = new SectorsCage[rowCount][columnCount];

        for (int rowNumber = 0; rowNumber < rowCount; rowNumber++) {
            for (int columnNumber = 0; columnNumber < columnCount; columnNumber++) {
                sector[rowNumber][columnNumber] = new SectorsCage(rowNumber, columnNumber, isBusy(fillFactor));
            }
        }
    }

    public Map<RiskGroup, Integer> determineRiskGroups() {
        Map<RiskGroup, Integer> groupsCountByRisk = new HashMap<>();
        for (RiskGroup riskGroup: RiskGroup.values()) {
            groupsCountByRisk.put(riskGroup, 0);
        }

        List<Integer> countPersonsInGroups = calculationCountOfPeopleInGroups();
        for (Integer count: countPersonsInGroups) {
            RiskGroup riskGroup = RiskGroup.identifyGroup(count);
            int last = groupsCountByRisk.get(riskGroup);
            groupsCountByRisk.put(riskGroup, ++last);
        }

        return groupsCountByRisk;
    }

    @Override
    public String toString() {
        String result = "\t ";
        for (int columnNumber = 1; columnNumber <= getColumnCount(); columnNumber++) {
            result += columnNumber + "\t ";
        }
        for (int rowNumber = 0; rowNumber < getRowCount(); rowNumber++) {
            result += "\n" + (rowNumber + 1) + "\t";
            for (int columnNumber = 0; columnNumber < getColumnCount(); columnNumber++) {
                if (sector[rowNumber][columnNumber].isBusy()) {
                    result += "|X|\t";
                } else {
                    result += " - \t";
                }
            }
        }
        return result;
    }

    private List<Integer> calculationCountOfPeopleInGroups() {
        List<Integer> countPersonsInGroups = new ArrayList<>();

        for (int rowNumber = 0; rowNumber < getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < getColumnCount(); columnNumber++) {
                SectorsCage cage = sector[rowNumber][columnNumber];
                if (!cage.isChecked() && cage.isBusy()) {
                    countPersonsInGroups.add(calculateCountNeighbors(cage));
                }
            }
        }
        return countPersonsInGroups;
    }

    private int calculateCountNeighbors(SectorsCage firstCage) {

        int countPeople = 0;
        Deque<SectorsCage> neighbors = new ArrayDeque<>();
        neighbors.offer(firstCage);
        SectorsCage cage;
        while ((cage = neighbors.poll()) != null) {
            countPeople++;
            cage.setChecked(true);
            for (SectorsCage cageNext: getNeighbors(cage)) {
                neighbors.offer(cageNext);
            }
        }
        return countPeople;
    }

    private List<SectorsCage> getNeighbors(SectorsCage cage) {
        List<SectorsCage> neighbors = new ArrayList<>();
        neighbors.add(getCage(cage.getRowNumber(), cage.getColumnNumber() - 1));
        neighbors.add(getCage(cage.getRowNumber(), cage.getColumnNumber() + 1));
        neighbors.add(getCage(cage.getRowNumber() - 1, cage.getColumnNumber()));
        neighbors.add(getCage(cage.getRowNumber() + 1, cage.getColumnNumber()));
        for (int i = 0; i <  neighbors.size(); i++) {
            if (!isBusyAndIsUnchecked(neighbors.get(i))) {
                neighbors.remove(i);
                i--;
            }
        }
        return neighbors;
    }

    private boolean isBusyAndIsUnchecked(SectorsCage cage) {
        return cage != null && cage.isBusy() && !cage.isChecked();
    }

    private SectorsCage getCage(int rowNumber, int columnNumber) {
        if (rowNumber < 0 || rowNumber >= getRowCount() || columnNumber < 0 || columnNumber >= getColumnCount()) {
            return null;
        }
        return sector[rowNumber][columnNumber];
    }

    private int getRowCount() {
        return sector.length;
    }

    private int getColumnCount() {
        return sector[0].length;
    }

    private boolean isBusy(double fillFactor) {
        Random random = new Random();
        return random.nextDouble() < fillFactor;
    }
}
