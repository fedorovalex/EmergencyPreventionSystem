package cmdLine;

import cmdLine.exception.ParametersException;
import org.apache.commons.cli.*;
import surveillanceSector.sector.SectorParameters;

public class CmdLine {

    public SectorParameters parse(String[] args) throws ParseException, ParametersException {

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = cmdLinePosixParser.parse(setOptions(), args);

        if (!commandLine.hasOption("y")) {
            throw new ParametersException("Не указано количество строк.");
        }
        if (!commandLine.hasOption("x")) {
            throw new ParametersException("Не указано количество столбцов.");
        }
        if (!commandLine.hasOption("f")) {
            throw new ParametersException("Не указана вероятность заполнения сектора.");
        }

        int rowCount = Integer.parseInt(commandLine.getOptionValue("y"));
        int columnCount = Integer.parseInt(commandLine.getOptionValue("x"));
        double fillFactor = Double.parseDouble(commandLine.getOptionValue("f"));

        return new SectorParameters(rowCount, columnCount, fillFactor);
    }

    private Options setOptions() {
        Option optionRowCount = new Option("y", "rowCount", true, "Row Count");
        Option optionColumnCount = new Option("x", "columnCount", true, "Column Count");
        Option optionFillFactor = new Option("f", "fillFactor", true, "Fill Factor");

        optionRowCount.setArgs(1);
        optionColumnCount.setArgs(1);
        optionFillFactor.setArgs(1);

        Options posixOptions = new Options();
        posixOptions.addOption(optionRowCount);
        posixOptions.addOption(optionColumnCount);
        posixOptions.addOption(optionFillFactor);

        return posixOptions;
    }
}
