package app;

import app.cmdLine.CmdLine;
import app.cmdLine.exception.ParametersException;
import app.reporter.Reporter;
import surveillancesector.sector.ConverterSector;
import surveillancesector.sector.creators.CreatorWithRandomFilling;
import surveillancesector.supervision.Inspector;
import surveillancesector.supervision.RiskGroup;
import surveillancesector.sector.Sector;
import org.apache.commons.cli.ParseException;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        CmdLine cmdLine = new CmdLine();
        InputParameters inputParameters = null;
        try {
            inputParameters = cmdLine.parse(args);
        }
        catch (ParseException | ParametersException e) {
            System.out.println(e.getMessage());
            System.out.println(cmdLine.getHelp());
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println(cmdLine.getHelp());
            System.exit(1);
        }

        Sector sector = new CreatorWithRandomFilling().create(
                inputParameters.getRowCount(),
                inputParameters.getColumnCount(),
                inputParameters.getFillFactor());

        Inspector inspector = new Inspector(sector);
        Map<RiskGroup, Long> countGroupsByRisk = inspector.determineRiskGroups();

        String report = new Reporter().makeReport(inputParameters, sector, countGroupsByRisk);

        System.out.println(report);
    }
}
