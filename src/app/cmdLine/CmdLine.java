package app.cmdLine;

import app.cmdLine.exception.ParametersException;
import org.apache.commons.cli.*;
import app.InputParameters;

public class CmdLine {

    public InputParameters parse(String[] args) throws ParseException, ParametersException {

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

        return new InputParameters(rowCount, columnCount, fillFactor);
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

    public String getHelp() {
        return "Нужно указать параметры для системы предупреждения чрезвычайных ситуаций:\n" +
                "-y 'Количество строк сектора' (целое число)\n" +
                "-x 'Количество стобцов сектора' (целое число)\n" +
                "-f 'вероятность заполнения ячейки сектора (от 0.0 до 1.0)'\n" +
                "\nПример: '-y 9 -x 9 -f 0.3'.";
    }
}
