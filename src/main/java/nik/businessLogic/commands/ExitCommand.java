package nik.businessLogic.commands;

import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

import java.util.Map;

public class ExitCommand implements Command {
    private ControlUnit controlUnit;

    public ExitCommand(ControlUnit controlUnit){
        controlUnit.addNewCommand("exit",this);
        controlUnit.addNewSettings(CommandType.CLEAR, "exit");

    }
    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if(!option.isEmpty()){
            resultShell.setCommandResult("Команда exit не содержит аргументов. Выполнение прервано");
            return;
        }

        System.exit(1);
    }

    @Override
    public String getCommandDescription() {
        return "exit: Выход из приложения без сохранения данных о коллекции";
    }
}
