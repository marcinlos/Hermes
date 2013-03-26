package rozprochy.common.hermes;

public interface ErrorHandler {
    
    boolean onError(CommandException e) throws CommandException;

}
