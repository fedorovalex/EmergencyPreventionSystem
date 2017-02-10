package surveillancesector.sector.creators;

import org.junit.Test;
import surveillancesector.sector.Sector;
import surveillancesector.sector.sectorsCell.Coordinate;

import static org.junit.Assert.*;
import static surveillancesector.sector.sectorsCell.ConditionCell.BUSY;
import static surveillancesector.sector.sectorsCell.ConditionCell.FREE;

public class CreatorWithFillingOfStringTest {

    @Test
    public void createTestByEmpty() throws Exception {

        char symbolFilling = '.';

        Sector sector = new CreatorWithFillingOfString().create(symbolFilling,
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx");

        for (int y = 0; y < sector.getRowCount(); y++) {
            for (int x = 0; x < sector.getColumnCount(); x++) {
                assertEquals(sector.getCell(new Coordinate(x, y)), FREE);
            }
        }

    }

    @Test
    public void createTestByAllFilled() throws Exception {

        Sector sector = new CreatorWithFillingOfString().create('x',
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx\n"+
                        "xxxxxxxxxxxx");

        for (int y = 0; y < sector.getRowCount(); y++) {
            for (int x = 0; x < sector.getColumnCount(); x++) {
                assertEquals(sector.getCell(new Coordinate(x, y)), BUSY);
            }
        }

    }

}