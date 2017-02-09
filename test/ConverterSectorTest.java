
import org.junit.Test;
import surveillancesector.sector.ConverterSector;
import surveillancesector.sector.creators.CreatorWithFillingOfString;
import surveillancesector.sector.Sector;
import surveillancesector.sector.sectorsCell.ConditionCell;

import static org.junit.Assert.*;

public class ConverterSectorTest {
    @Test
    public void convertToStringTest() throws Exception {

        Sector sector = CreatorWithFillingOfString.create('X',
                        "X X XX\n" +
                        " XX XX\n" +
                        "X  X X\n" +
                        "XXXXX \n" +
                        "X    X\n" +
                        " XXXXX\n" +
                        "XXXXXX\n" +
                        "XXXXXX\n"
        );

        String expected = "\t 1\t 2\t 3\t 4\t 5\t 6\t \n" +
                "1\t|X|\t - \t|X|\t - \t|X|\t|X|\t\n" +
                "2\t - \t|X|\t|X|\t - \t|X|\t|X|\t\n" +
                "3\t|X|\t - \t - \t|X|\t - \t|X|\t\n" +
                "4\t|X|\t|X|\t|X|\t|X|\t|X|\t - \t\n" +
                "5\t|X|\t - \t - \t - \t - \t|X|\t\n" +
                "6\t - \t|X|\t|X|\t|X|\t|X|\t|X|\t\n" +
                "7\t|X|\t|X|\t|X|\t|X|\t|X|\t|X|\t\n" +
                "8\t|X|\t|X|\t|X|\t|X|\t|X|\t|X|\t";
        assertEquals(expected, new ConverterSector().convertToString(sector));
    }
}