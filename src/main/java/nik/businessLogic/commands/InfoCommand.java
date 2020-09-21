package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

public class InfoCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;

    public InfoCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;

        controlUnit.addNewCommand("info", this);
        controlUnit.addNewSettings(CommandType.CLEAR, "info");
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if (!option.isEmpty()){
            resultShell.setCommandResult("команда info не содержит аргументов");
            return;
        }

        resultShell.setCommandResult(collectionShell.getInfo());
    }

    @Override
    public String getCommandDescription() {
        return "info: инфомация о коллекции";
    }
}
