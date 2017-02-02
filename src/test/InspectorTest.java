package test;

import org.junit.Test;
import surveillanceSector.sector.Creator;
import surveillanceSector.sector.Sector;
import surveillanceSector.sector.sectorsCell.ConditionCell;
import surveillanceSector.supervision.Inspector;
import surveillanceSector.supervision.RiskGroup;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class InspectorTest {
    @Test
    public void determineRiskGroupsTest() throws Exception {

        ConditionCell busy = ConditionCell.BUSY;
        ConditionCell freely = ConditionCell.FREELY;

        ConditionCell[][] cells = {
                {busy, freely, busy, freely, busy, busy},
                {freely, busy, busy, freely, busy, busy},
                {busy, freely, freely, busy, freely, busy},
                {busy, busy, busy, busy, busy, freely},
                {busy, freely, freely, freely, freely, busy},
                {freely, busy, busy, busy, busy, busy},
                {busy, busy, busy, busy, busy, freely},
                {busy, busy, busy, busy, freely, busy},
        };

        Sector sector = Creator.createSector(8, 6, cells);
        Inspector inspector = new Inspector(sector);

        Map<RiskGroup, Integer> expectedRiskGroups = new HashMap<>();
        expectedRiskGroups.put(RiskGroup.NONE, 2);
        expectedRiskGroups.put(RiskGroup.NORMAL, 1);
        expectedRiskGroups.put(RiskGroup.MINOR, 1);
        expectedRiskGroups.put(RiskGroup.MAJOR, 1);
        expectedRiskGroups.put(RiskGroup.CRITICAL, 1);

        Map<RiskGroup, Integer> riskGroups = inspector.determineRiskGroups();

        assertTrue(
                Arrays.deepEquals(expectedRiskGroups.keySet().toArray(), riskGroups.keySet().toArray()) &&
                Arrays.equals(expectedRiskGroups.entrySet().toArray(), riskGroups.entrySet().toArray())
                );

    }

}