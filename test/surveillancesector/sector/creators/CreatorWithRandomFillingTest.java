package surveillancesector.sector.creators;

import org.junit.Test;
import surveillancesector.sector.Sector;
import surveillancesector.sector.sectorsCell.Coordinate;

import static org.junit.Assert.*;
import static surveillancesector.sector.sectorsCell.ConditionCell.*;

public class CreatorWithRandomFillingTest {

    @Test
    public void createTestByEmpty() throws Exception {
        Sector sector = new CreatorWithRandomFilling().create(10, 10, 0);

        for (int y = 0; y < sector.getRowCount(); y++) {
            for (int x = 0; x < sector.getColumnCount(); x++) {
                assertEquals(sector.getCell(new Coordinate(x, y)), FREE);
            }
        }
    }

    @Test
    public void createTestByAllFilled() throws Exception {
        Sector sector = new CreatorWithRandomFilling().create(10, 10, 1);

        for (int y = 0; y < sector.getRowCount(); y++) {
            for (int x = 0; x < sector.getColumnCount(); x++) {
                assertEquals(sector.getCell(new Coordinate(x, y)), BUSY);
            }
        }
    }
}