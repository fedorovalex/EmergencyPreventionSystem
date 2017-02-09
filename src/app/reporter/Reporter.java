package app.reporter;

import app.InputParameters;
import surveillancesector.sector.ConverterSector;
import surveillancesector.sector.Sector;
import surveillancesector.supervision.RiskGroup;

import java.util.Map;

public class Reporter {

    public String makeReport(InputParameters inputParameters, Sector sector, Map<RiskGroup, Long> countGroupsByRisk) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(sectorSizeToString(inputParameters));
        stringBuilder.append("\n");
        stringBuilder.append(fillFactorToString(inputParameters));
        stringBuilder.append("\n");
        stringBuilder.append(collectRiskGroupToString());
        stringBuilder.append("\n");
        stringBuilder.append(new ConverterSector().convertToString(sector));
        stringBuilder.append("\n");
        stringBuilder.append(collectGroupCountByRiskToString(countGroupsByRisk));

        return stringBuilder.toString();
    }

    private String collectRiskGroupToString() {

        String result = "";
        for (RiskGroup riskGroup: RiskGroup.values()) {
            result += riskGroup + " от " + riskGroup.getMinCount() + " до " + riskGroup.getMaxCount() + "\n";
        }
        return result;
    }

    private String collectGroupCountByRiskToString(Map<RiskGroup, Long> map) {

        String result = "";
        for (RiskGroup riskGroup: RiskGroup.values()) {
            result += riskGroup + "(" + riskGroup.getMinCount() + " - " + riskGroup.getMaxCount() +
                    ") -> " + map.get(riskGroup) + "\n";
        }
        return result;
    }

    private String sectorSizeToString(InputParameters inputParameters) {
        return "Размер матрицы " + inputParameters.getRowCount() + " X " + inputParameters.getColumnCount();
    }

    private String fillFactorToString(InputParameters inputParameters) {
        return "Вероятность заполнения поля " + inputParameters.getFillFactor();
    }
}
