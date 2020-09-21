package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

import java.util.Map;

public class ClearCommand implements Command {

    private final CollectionShell collectionShell;
    private ControlUnit controlUnit;

    public ClearCommand(CollectionShell collectionShell, ControlUnit controlUnit){
        this.collectionShell = collectionShell;
        this.controlUnit = controlUnit;

        controlUnit.addNewCommand("clear",this);
        controlUnit.addNewSettings(CommandType.CLEAR, "clear");
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if(!option.isEmpty()){
            resultShell.setCommandResult("Команда clear не содержит аргументов! Выполнение прервано");
            return;
        }

        collectionShell.clearAll();
        resultShell.setCommandResult("Коллекция была очищена");
    }

    @Override
    public String getCommandDescription() {
        return "Clear: команда , очищающая коллекцию от объектов";
    }
}
