package nik.businessLogic.commands;

import com.google.gson.Gson;
import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.sourceClasses.Person;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterByOwnerCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;

    public FilterByOwnerCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;

        controlUnit.addNewCommand("filter_by_owner", this);
        controlUnit.addNewSettings(CommandType.ARG, "filter_by_owner");
    }

    @Override
    public void execute( String option, ResultShell resultShell) throws CommandExecutingException {
        if (option.isEmpty()) {
            resultShell.setCommandResult("Данная команда должна содержать аргументы!");
           return;
        }

        for (Product product : collectionShell.getProductList()) {
            if (product.getOwner().getName().equals(option)) {
                resultShell.setCommandResult(product.toString() + "\n");
            }
        }

        if (resultShell.getCommandResult().isEmpty()) {
            resultShell.setCommandResult("Не найдено продуктов с таким хозяином");
        }

    }

    @Override
    public String getCommandDescription() {
        return "filter_by_owner: Вываодит список продуктов с указаным именем хозяина";
    }
}
