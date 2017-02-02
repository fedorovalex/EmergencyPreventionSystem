package test;

import org.junit.Test;
import surveillanceSector.sector.ConverterSector;
import surveillanceSector.sector.Creator;
import surveillanceSector.sector.Sector;
import surveillanceSector.sector.sectorsCell.ConditionCell;

import static org.junit.Assert.*;

public class ConverterSectorTest {
    @Test
    public void convertToStringTest() throws Exception {

        ConditionCell busy = ConditionCell.BUSY;
        ConditionCell freely = ConditionCell.FREELY;

        ConditionCell[][] cells = {
                {busy, freely, busy, freely, busy, busy},
                {freely, busy, busy, freely, busy, busy},
                {busy, freely, freely, busy, freely, busy},
                {busy, busy, busy, busy, busy, freely},
                {busy, freely, freely, freely, freely, busy},
                {freely, busy, busy, busy, busy, busy},
                {busy, busy, busy, busy, busy, busy},
                {busy, busy, busy, busy, busy, busy},
        };
        Sector sector = Creator.createSector(8, 6, cells);

        String expected = "\t 1\t 2\t 3\t 4\t 5\t 6\t \n" +
                "1\t|X|\t - \t|X|\t - \t|X|\t|X|\t\n" +
                "2\t - \t|X|\t|X|\t - \t|X|\t|X|\t\n" +
                "3\t|X|\t - \t - \t|X|\t - \t|X|\t\n" +
                "4\t|X|\t|X|\t|X|\t|X|\t|X|\t - \t\n" +
                "5\t|X|\t - \t - \t - \t - \t|X|\t\n" +
                "6\t - \t|X|\t|X|\t|X|\t|X|\t|X|\t\n" +
                "7\t|X|\t|X|\t|X|\t|X|\t|X|\t|X|\t\n" +
                "8\t|X|\t|X|\t|X|\t|X|\t|X|\t|X|\t";
        assertEquals(expected, ConverterSector.convertToString(sector));
    }

}