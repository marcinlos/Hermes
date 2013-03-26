package rozprochy.common.cli;

public class NoSuchCommandException extends CommandException {
    
    private String command;

    public NoSuchCommandException(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }

}
