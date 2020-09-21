package nik.businessLogic.commands;

import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.fileWorker.IOInterface;
import nik.businessLogic.workClasses.ResultShell;

public class LoadCommand implements Command{
    private ControlUnit controlUnit;
    private IOInterface fileManager;
    private String commandName;

    public LoadCommand(ControlUnit controlUnit, IOInterface fileManager) {
        this.controlUnit = controlUnit;
        this.fileManager = fileManager;
        commandName = "load";

        controlUnit.addNewCommand(commandName, this);
        controlUnit.addNewSettings(CommandType.CLEAR, commandName);
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        fileManager.read(resultShell);
    }

    @Override
    public String getCommandDescription() {
        return null;
    }
}
