package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

public class RemoveCommand implements Command{
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;
    private String commandName;

    public RemoveCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;
        commandName = "remove";

        controlUnit.addNewCommand(commandName,this);
        controlUnit.addNewSettings(CommandType.ARG, commandName);


    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if(option.isEmpty()){
            resultShell.setCommandResult("команда " + commandName + " должна содержать аргумент");
             return;
        }

        try {
             Integer removeId = Integer.parseInt(option);
             collectionShell.removeProductById(removeId);
             resultShell.setCommandResult("Продукт успешно удален. Айди удаленного продукта: "+ removeId);
        } catch (NullPointerException exception){
            resultShell.setCommandResult("элемента с заданным айди не существует");
        }  catch ( Exception e){
            resultShell.setCommandResult("Аргумент команды невалиден");
            throw new CommandExecutingException();
        }

    }

    @Override
    public String getCommandDescription() {
        return "remove: удаляет продукт по айди";
    }
}
