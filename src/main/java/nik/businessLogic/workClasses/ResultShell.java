package nik.businessLogic.workClasses;

public class ResultShell {
    private String commandResult = "";

    public ResultShell(String commandResult) {
        this.commandResult = commandResult;
    }

    public ResultShell(){

    }

    public String getCommandResult() {
        return commandResult;
    }

    public void setCommandResult(String commandResult) {
        this.commandResult += commandResult + "\n";
    }
}
