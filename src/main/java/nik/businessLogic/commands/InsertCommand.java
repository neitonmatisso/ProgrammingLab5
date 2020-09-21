package nik.businessLogic.commands;

import com.google.gson.Gson;
import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

public class InsertCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;

    public InsertCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;

        controlUnit.addNewCommand("insert", this);
        controlUnit.addNewSettings(CommandType.OBJECT, "insert");
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if (option.isEmpty()){
            resultShell.setCommandResult("команда insert должна содержать аргумент");
           return;
        }

        Product product = new Gson().fromJson(option, Product.class);
        System.out.println(product);
        collectionShell.addNewProduct(product);
        resultShell.setCommandResult("Объект успешно добавлен");
    }

    @Override
    public String getCommandDescription() {
        return "insert: добавляет новый элемент в коллекцию";
    }
}
