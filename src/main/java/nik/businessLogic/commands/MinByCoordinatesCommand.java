package nik.businessLogic.commands;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.sourceClasses.Coordinates;
import nik.businessLogic.sourceClasses.Person;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

import java.util.Comparator;

public class MinByCoordinatesCommand  implements Command{
    private ControlUnit controlUnit;
    private CollectionShell collectionShell;
    private String commandName;

    public MinByCoordinatesCommand(ControlUnit controlUnit, CollectionShell collectionShell) {
        this.controlUnit = controlUnit;
        this.collectionShell = collectionShell;
        commandName = "min_by_coordinate";

        controlUnit.addNewCommand( commandName , this);
        controlUnit.addNewSettings(CommandType.CLEAR, commandName);
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if(!option.isEmpty()) {
            resultShell.setCommandResult("команда " +commandName+ " не должна содержать аргументов");
            return;
        }
        if(collectionShell.isEmpty()){
            resultShell.setCommandResult("коллекция пуста");
            return;
        }
         resultShell.setCommandResult( collectionShell.getProductList().stream()
                    .min(Comparator.comparingInt(x -> x.getCoordinates().getX()))
                    .get()
                    .toString()
         );
    }

    @Override
    public String getCommandDescription() {
        return "min_by_coordinate: выводит продукт с минимальной координатой";
    }
}
