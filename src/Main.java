import surveillanceSector.RiskGroup;
import surveillanceSector.SurveillanceSector;

import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SurveillanceSector sector = new SurveillanceSector(25, 25, 0.3);
        System.out.println(sector.toString());
        Map<RiskGroup, Integer> map =  sector.determineRiskGroups();
        for (RiskGroup riskGroup: map.keySet()) {
            System.out.println(riskGroup + "(" + riskGroup.getMaxCount() + ") -> " + map.get(riskGroup));
        }
    }
}
