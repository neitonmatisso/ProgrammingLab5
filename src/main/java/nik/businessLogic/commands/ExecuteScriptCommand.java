package nik.businessLogic.commands;

import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.factories.ProductFactory;
import nik.businessLogic.workClasses.ResultShell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    private ControlUnit controlUnit;
    private String commandName;
    private ProductFactory productFactory = new ProductFactory();

    public ExecuteScriptCommand(ControlUnit controlUnit){
         this.controlUnit = controlUnit;
         commandName = "execute_script";

         controlUnit.addNewSettings(CommandType.SCRIPT, commandName);
         controlUnit.addNewCommand(commandName, this);
    }
    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        File scriptFile = new File(option);
        Scanner scanner;
        try {
            scanner = new Scanner(scriptFile);
        } catch (FileNotFoundException e) {
            resultShell.setCommandResult("файл не найден");
            return;
        }

        while (scanner.hasNextLine()){
            String currentCommand = scanner.nextLine();
            List<String> commandInfo = Arrays.asList(currentCommand.split(" "));
            CommandType currentCommandType = controlUnit.getTypeByName(commandInfo.get(0));
            System.out.println(currentCommand + " " + currentCommandType);
            switch (currentCommandType){
                case CLEAR:
                    controlUnit.executeCommand(commandInfo.get(0),"", resultShell );
                    break;
                case ARG:
                    controlUnit.executeCommand(commandInfo.get(0),commandInfo.get(1), resultShell );
                    break;
                case OBJECT:
                    List<String> argList = new ArrayList<>();
                    for (int i = 0; i<8; i++){
                        argList.add(scanner.nextLine());
                    }
                    System.out.println(argList);
                    controlUnit.executeCommand(commandInfo.get(0),productFactory.createProductWithParams(argList),resultShell);
                    break;
            }
        }

    }

    @Override
    public String getCommandDescription() {
        return "execute_script: выполяет скрипт из заданного файла";
    }
}
