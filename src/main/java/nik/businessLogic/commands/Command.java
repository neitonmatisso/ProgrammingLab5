package nik.businessLogic.commands;

import nik.businessLogic.exceptions.CommandExecutingException;
import nik.businessLogic.workClasses.ResultShell;

import java.util.List;
import java.util.Map;

/**
 * Общий интерфейс для всех команд.
 */
public interface Command {
    /**
     * Основной метод люблой из команд. Каждая команда реализует метод по своему
     * @param option - какие-то данные , которые нужны дял выполнения команды
     * @param resultShell - обертка для результата
     */
    void execute(String option, ResultShell resultShell) throws CommandExecutingException;

    String getCommandDescription();

}
