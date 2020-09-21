package nik.businessLogic.commands;

import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

public class HelpCommand implements Command{
    private ControlUnit controlUnit;
    private String commandName;

    public HelpCommand(ControlUnit controlUnit){
        this.controlUnit = controlUnit;
        commandName = "help";

        controlUnit.addNewCommand(commandName,this);
        controlUnit.addNewSettings(CommandType.CLEAR, commandName);
    }

    @Override
    public void execute(String option, ResultShell resultShell) throws CommandExecutingException {
        if(!option.isEmpty()){
            resultShell.setCommandResult("Данная команда не содержит аргументов");
            return;
        }
        for(Command command : controlUnit.getAllCommand()){
            resultShell.setCommandResult(command.getCommandDescription());
        }
    }

    @Override
    public String getCommandDescription() {
        return "help: help me , I study in ВТ";
    }
}
