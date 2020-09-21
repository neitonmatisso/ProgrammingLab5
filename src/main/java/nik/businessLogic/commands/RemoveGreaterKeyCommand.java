package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

public class RemoveGreaterKeyCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;
    private String commandName;

    public RemoveGreaterKeyCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;
        commandName = "remove_greater_key";

        controlUnit.addNewCommand(commandName,this);
        controlUnit.addNewSettings(CommandType.CLEAR, commandName);
    }


    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if (option.isEmpty()){
            resultShell.setCommandResult("команда remove greater  должна содержать аргумент");
            return;
        }

        try {
            collectionShell.getProductList().removeIf(x -> x.getId().equals(Integer.parseInt(option)));
            resultShell.setCommandResult("Объекты удалены");
        } catch (Exception exception){
            resultShell.setCommandResult("Аргумент невалиден");
        }

    }

    @Override
    public String getCommandDescription() {
        return "remove_greater_key: Удаляет элементы, айди которых превышает заданный";
    }
}
