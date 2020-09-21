package nik.businessLogic.commands;

import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.fileWorker.IOInterface;
import nik.businessLogic.workClasses.ResultShell;

public class SaveCommand implements Command {
    private ControlUnit controlUnit;
    private IOInterface fileManager;
    private String commandName;

    public SaveCommand(ControlUnit controlUnit, IOInterface fileManager) {
        this.controlUnit = controlUnit;
        this.fileManager = fileManager;
        commandName = "save";

        controlUnit.addNewCommand(commandName, this);
        controlUnit.addNewSettings(CommandType.CLEAR, commandName);
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        fileManager.write(resultShell);
    }

    @Override
    public String getCommandDescription() {
        return "save: сохраняет в коллекцию";
    }
}
