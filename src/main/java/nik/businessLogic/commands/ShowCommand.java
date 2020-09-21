package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

public class ShowCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;
    private String commandName;
    public ShowCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;
        commandName = "show";

        controlUnit.addNewCommand(commandName,this);
        controlUnit.addNewSettings(CommandType.CLEAR, commandName);
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if (!option.isEmpty()){
            resultShell.setCommandResult("команда show не содержит аргументов");
            return;
        }
        for (Product product: collectionShell.getProductList()) {
            resultShell.setCommandResult(product.toString());
        }
    }

    @Override
    public String getCommandDescription() {
        return "show: выводит коллекцию";
    }
}
