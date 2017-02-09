package surveillancesector.sector.creators;

import surveillancesector.sector.Sector;
import surveillancesector.sector.sectorsCell.ConditionCell;
import surveillancesector.sector.sectorsCell.Coordinate;


public class CreatorWithFillingOfString {

    public static Sector create(char symbolFilling, String str) {

        assert str != null;
        String[] rows = str.split("\\n");
        assert isAvailable(rows);

        Sector sector = new Sector(rows.length, rows[0].length());

        for (int y = 0; y < rows.length; y++) {
            for (int x = 0; x < rows[0].length(); x++) {
                sector.setCell(new Coordinate(x, y), charToConditionCell(rows[y].charAt(x), symbolFilling));
            }
        }

        return sector;
    }

    private static ConditionCell charToConditionCell(char symbol, char symbolFilling) {
        if (symbol == symbolFilling) {
            return ConditionCell.BUSY;
        }
        return ConditionCell.FREE;
    }

    private static boolean isAvailable(String[] rows) {

        for (String str: rows) {
            if (rows[0].length() != str.length()) {
                return false;
            }
        }
        return true;
    }
}
