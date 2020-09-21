package nik.businessLogic;

import nik.businessLogic.collectionWorker.CollectionShell;
import nik.businessLogic.collectionWorker.HashMapShell;
import nik.businessLogic.commands.*;
import nik.businessLogic.factories.ProductFactory;
import nik.businessLogic.fileWorker.FileManager;
import nik.businessLogic.fileWorker.IOInterface;
import nik.businessLogic.sourceClasses.Product;
import nik.businessLogic.workClasses.ResultShell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

public class MainClass {
    private static ControlUnit controlUnit;
    public static void main(String[] args) {
        loadContext();
        Scanner scanner = new Scanner(System.in);
        ProductFactory productFactory = new ProductFactory();
        while (true){
            System.out.print("> ");
            String commandInfo = scanner.nextLine();
            if (commandInfo.equals("")){
                continue;
            }
            List<String> commandInfoList = Arrays.asList(commandInfo.split(" "));
            ResultShell resultShell = new ResultShell();
            String commandName = commandInfoList.get(0);
            try {
                switch (controlUnit.getTypeByName(commandName)){
                    case OBJECT:
                        String serialObjecr = productFactory.getNewProduct();
                        controlUnit.executeCommand(commandName,serialObjecr,resultShell);
                        break;
                    case CLEAR:
                    case ARG:
                    case SCRIPT:
                        controlUnit.executeCommand(commandName,commandInfoList.get(1),resultShell);
                        break;

                }
                System.out.println(resultShell.getCommandResult());
            } catch (NullPointerException exception){
                System.out.println("такой команды не существует");
            }
        }
    }


    public static void loadContext(){
        controlUnit = new ControlUnit();
        CollectionShell collectionShell = new HashMapShell();

    //    IOInterface fileManager = new FileManager(collectionShell,System.getenv("filePath")); // Для того , чтобы достать путь к файлу из переменной среды

        IOInterface fileManager = new FileManager(collectionShell,"saveFile");

        Command clearCommand = new ClearCommand(collectionShell,controlUnit);
        Command exitCommand = new ExitCommand(controlUnit);
        Command filter = new FilterByOwnerCommand(controlUnit,collectionShell);
        Command filter_less = new FilterLessCommand(controlUnit,collectionShell);
        Command infoCommand = new InfoCommand(controlUnit,collectionShell);
        Command insertCommand = new InsertCommand(controlUnit,collectionShell);
        Command loadCommand = new LoadCommand(controlUnit,fileManager);
        Command minByCoordinates = new MinByCoordinatesCommand(controlUnit,collectionShell);
        Command removeCommand = new RemoveCommand(controlUnit,collectionShell);
        Command removeGreaterCommand = new RemoveGreaterCommand(controlUnit,collectionShell);
        Command removeGreaterKeyCommand = new RemoveGreaterKeyCommand(controlUnit,collectionShell);
        Command saveCommand = new SaveCommand(controlUnit,fileManager);
        Command showCommand = new ShowCommand(controlUnit,collectionShell);
        Command script = new ExecuteScriptCommand(controlUnit);
        Command help = new HelpCommand(controlUnit);

        controlUnit.executeCommand("load", "", new ResultShell());

    }
}
