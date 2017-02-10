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
    private AccountingVisitSector accountingVisitSector;

    public Inspector(Sector sector) {

        this.sector = sector;
    }

    public Map<RiskGroup, Long> determineRiskGroups() {

        accountingVisitSector = new AccountingVisitSector(sector.getRowCount(), sector.getColumnCount());

        Map<RiskGroup, Long> groupsCountByRisk = calculateSizeGroups().stream()
                .collect(
                        Collectors.groupingBy(
                                RiskGroup::identifyGroup, Collectors.counting()
                        )
                );

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
                if (cell == BUSY && !accountingVisitSector.isVisited(coo(x, y))) {
                    sizeGroups.add(calculateNeighborsCount(coo(x, y)));
                }
            }
        }
        return sizeGroups;
    }

    private int calculateNeighborsCount(Coordinate first) {

        int peoplesCount = 0;
        Deque<Coordinate> neighbors = new ArrayDeque<>();
        accountingVisitSector.toVisit(first);
        neighbors.offer(first);
        Coordinate coordinate;
        while ((coordinate = neighbors.poll()) != null) {
            peoplesCount++;

            getNeighbors(coordinate)
                    .filter(neighbor -> !accountingVisitSector.isVisited(neighbor))
                    .peek(accountingVisitSector::toVisit)
                    .forEach(neighbors::offer);
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