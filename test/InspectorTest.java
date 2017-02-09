
import org.junit.Test;

import surveillancesector.sector.creators.CreatorWithFillingOfString;
import surveillancesector.sector.Sector;
import surveillancesector.supervision.Inspector;
import surveillancesector.supervision.RiskGroup;

import java.util.*;

import static org.junit.Assert.*;
import static surveillancesector.supervision.RiskGroup.*;

public class InspectorTest {

    @Test
    public void determineRiskGroupsTest() throws Exception {

        Sector sector = CreatorWithFillingOfString.create('x',
                        " x x xxx    \n"+
                        " x   xxx  x \n"+
                        "  x  xxx  x \n"+
                        "xx x      x \n"+
                        "xx xxxxx  x \n"+
                        "x   x  x  x \n"+
                        "x   xxxx    \n"+
                        "  xxx  x    ");

        Map<RiskGroup, Long> result = new Inspector(sector).determineRiskGroups();
        assertEquals(result.get(NONE), new Long(3L));
        assertEquals(result.get(MINOR), new Long(0L));
        assertEquals(result.get(NORMAL), new Long(2L));
        assertEquals(result.get(MAJOR), new Long(1L));
        assertEquals(result.get(CRITICAL), new Long(1L));
    }

    @Test
    public void determineRiskGroupsTestByFreeAll() throws Exception {

        Sector sector = CreatorWithFillingOfString.create('x',
                        "            \n"+
                        "            \n"+
                        "            \n"+
                        "            \n"+
                        "            \n"+
                        "            \n"+
                        "            \n"+
                        "            ");

        Map<RiskGroup, Long> result = new Inspector(sector).determineRiskGroups();

        Arrays.stream(RiskGroup.values())
                .map(result::get)
                .forEach(
                        riskGroupCount -> assertEquals(riskGroupCount, new Long(0L))
                );
    }

    @Test
    public void determineRiskGroupsTestByBoundaries() throws Exception {

        Sector sector = CreatorWithFillingOfString.create('x',
                        "xxxxxxxxxxxx\n"+
                        "x          x\n"+
                        "x          x\n"+
                        "x     x    x\n"+
                        "x          x\n"+
                        "x          x\n"+
                        "x          x\n"+
                        "xxxxxxxxxxxx");

        Map<RiskGroup, Long> result = new Inspector(sector).determineRiskGroups();

        assertEquals(result.get(NONE), new Long(1L));
        assertEquals(result.get(MINOR), new Long(0L));
        assertEquals(result.get(NORMAL), new Long(0L));
        assertEquals(result.get(MAJOR), new Long(0L));
        assertEquals(result.get(CRITICAL), new Long(1L));
    }
}
