package nik.businessLogic.commands;

import com.google.gson.Gson;
import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

public class RemoveGreaterCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;
    private String commandName;

    public RemoveGreaterCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;
        commandName = "remove_greater";

        controlUnit.addNewCommand(commandName, this);
        controlUnit.addNewSettings(CommandType.OBJECT, commandName);
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if (option.isEmpty()){
            resultShell.setCommandResult("команда remove должна содержать аргумент");
            return;
        }

        Product optionProduct = new Gson().fromJson(option, Product.class);

        for (Product product : collectionShell.getProductList()) {
            if(product.getPrice() > optionProduct.getPrice()){
                collectionShell.removeProductById(product.getId());
            }
        }

    }

    @Override
    public String getCommandDescription() {
        return "remove_greater: удаляет все элементы , превыщающие заданный";
    }
}
