import cmdLine.CmdLine;
import cmdLine.exception.ParametersException;
import surveillanceSector.sector.ConverterSector;
import surveillanceSector.sector.Creator;
import surveillanceSector.supervision.Inspector;
import surveillanceSector.supervision.RiskGroup;
import surveillanceSector.sector.SectorParameters;
import surveillanceSector.sector.Sector;
import org.apache.commons.cli.ParseException;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        CmdLine cmdLine = new CmdLine();
        Sector sector;

        try {
            SectorParameters sectorParameters = cmdLine.parse(args);
            sector = Creator.createSector(sectorParameters);
            Inspector inspector = new Inspector(sector);

            String sectorSize = "Размер матрицы " + sectorParameters.getRowCount() +
                    " X " + sectorParameters.getColumnCount();
            String fillFactor = "Вероятность заполнения поля " + sectorParameters.getFillFactor();
            String riskGroup = collectRiskGroupToString();
            String groupCountByRisk = collectGroupCountByRiskToString(inspector.determineRiskGroups());

            System.out.println(
                    sectorSize + "\n"
                    + fillFactor + "\n" + riskGroup + "\n"
                    + ConverterSector.convertToString(sector)
                    + "\n" + groupCountByRisk);
        }
        catch (ParseException | ParametersException e) {
            System.out.println(e.getMessage());
            System.out.println(getHelp());
        }
        catch (Exception e) {
            System.out.println(getHelp());
        }

        /*
        SurveillanceSector sector = new SurveillanceSector(9, 9, mas);
        Observer observer = new Observer(sector);

        String riskGroup = collectRiskGroupToString();
        String groupCountByRisk = collectGroupCountByRiskToString(observer.determineRiskGroups());

        System.out.println(riskGroup + "\n" + sector.collectToString()
                + "\n" + groupCountByRisk);*/
    }

    private static String collectRiskGroupToString() {

        String result = "";
        for (RiskGroup riskGroup: RiskGroup.values()) {
            result += riskGroup + " от " + riskGroup.getMinCount() + " до " + riskGroup.getMaxCount() + "\n";
        }
        return result;
    }

    private static String collectGroupCountByRiskToString(Map<RiskGroup, Integer> map) {

        String result = "";
        for (RiskGroup riskGroup: RiskGroup.values()) {
            result += riskGroup + "(" + riskGroup.getMinCount() + " - " + riskGroup.getMaxCount() +
                    ") -> " + map.get(riskGroup) + "\n";
        }
        return result;
    }

    private static String getHelp() {
        return "Нужно указать параметры для системы предупреждения чрезвычайных ситуаций:\n" +
                "-y 'Количество строк сектора' (целое число)\n" +
                "-x 'Количество стобцов сектора' (целое число)\n" +
                "-f 'вероятность заполнения ячейки сектора (от 0.0 до 1.0)'\n" +
                "\nПример: '-y 9 -x 9 -f 0.3'.";
    }
}
