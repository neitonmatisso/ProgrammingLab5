package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

public class FilterLessCommand implements Command {
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;

    public FilterLessCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;

        controlUnit.addNewCommand("filter", this);
        controlUnit.addNewSettings(CommandType.ARG, "filter");
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if(option.isEmpty()){
            resultShell.setCommandResult("Команда фильтрации должна содержать аргумент");
           return;
        }
        Integer partNumber;
        try {
             partNumber = Integer.parseInt(option);
        } catch (Exception exception){
            resultShell.setCommandResult("Аргумент должен быть числом!");
            throw new CommandExecutingException();
        }


        for(Product product : collectionShell.getProductList()){
            Integer currentProductPartNumber = Integer.parseInt(product.getPartNumber());
            if(currentProductPartNumber < partNumber){
                resultShell.setCommandResult(product.toString() + "\n");
            }
        }

        if(resultShell.getCommandResult().isEmpty()){
            resultShell.setCommandResult("Не найдено товаров с номером партии меньше заданного");
        }


    }

    @Override
    public String getCommandDescription() {
        return "filter: Показывает элементы с партийным номером меньше заданного";
    }
}
