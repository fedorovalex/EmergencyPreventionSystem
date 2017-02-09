package surveillancesector.supervision;

import surveillancesector.sector.sectorsCell.ConditionCell;
import surveillancesector.sector.sectorsCell.Coordinate;
import surveillancesector.sector.Sector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static surveillancesector.sector.sectorsCell.ConditionCell.*;

public class Inspector {

    private Sector sector;
    private SectorAuditLog auditLog;

    public Inspector(Sector sector) {

        this.sector = sector;
    }

    public Map<RiskGroup, Long> determineRiskGroups() {

        auditLog = new SectorAuditLog(sector.getRowCount(), sector.getColumnCount());
        //List<Integer> sizeGroups = calculateSizeGroups();

        Map<RiskGroup, Long> groupsCountByRisk = calculateSizeGroups().stream()
                .collect(
                        Collectors.groupingBy(
                                RiskGroup::identifyGroup, Collectors.counting()
                        )
                );

        /*Map<RiskGroup, Integer> groupsCountByRisk = new HashMap<>();
        for (RiskGroup riskGroup: RiskGroup.values()) {
            groupsCountByRisk.put(riskGroup, 0);
        }


        for (Integer count: personsCountInGroups) {
            RiskGroup riskGroup = RiskGroup.identifyGroup(count);
            int oldValue = groupsCountByRisk.get(riskGroup);
            groupsCountByRisk.put(riskGroup, ++oldValue);
        }*/
        Arrays.stream(RiskGroup.values())
                .filter(riskGroup -> !groupsCountByRisk.containsKey(riskGroup))
                .forEach(riskGroup -> groupsCountByRisk.put(riskGroup, 0L));
        return groupsCountByRisk;
    }

    private List<Integer> calculateSizeGroups() {

        List<Integer> sizeGroups = new ArrayList<>();
        for (int y = 0; y < sector.getRowCount(); y++) {
            for (int x = 0; x < sector.getColumnCount(); x++) {
                ConditionCell cell = sector.getCell(coo(x, y));
                if (!auditLog.isVisited(coo(x, y)) && cell == BUSY) {
                    sizeGroups.add(calculateNeighborsCount(coo(x, y)));
                }
            }
        }
        return sizeGroups;
    }

    private int calculateNeighborsCount(Coordinate first) {

        int peoplesCount = 0;
        Deque<Coordinate> neighbors = new ArrayDeque<>();
        auditLog.toVisit(first);
        neighbors.offer(first);
        Coordinate coordinate;
        while ((coordinate = neighbors.poll()) != null) {
            peoplesCount++;

            getNeighbors(coordinate)
                    .filter(neighbor -> !auditLog.isVisited(neighbor))
                    .forEach(neighbor -> {
                        auditLog.toVisit(neighbor);
                        neighbors.offer(neighbor);
                    });
        }
        return peoplesCount;
    }

    private Stream<Coordinate> getNeighbors(Coordinate coordinate) {

        List<Coordinate> neighbors = new ArrayList<>();
        neighbors.add(coo(coordinate.getX(), coordinate.getY() - 1));
        neighbors.add(coo(coordinate.getX(), coordinate.getY() + 1));
        neighbors.add(coo(coordinate.getX() - 1, coordinate.getY()));
        neighbors.add(coo(coordinate.getX() + 1, coordinate.getY()));

        return neighbors.stream()
                .filter(neighbor -> sector.getCell(neighbor) == BUSY);
    }

    private Coordinate coo(int x, int y) {
        return new Coordinate(x, y);
    }
}