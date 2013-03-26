package rozprochy.common.cli;

public interface ErrorHandler {
    
    boolean onError(CommandException e) throws CommandException;

}
