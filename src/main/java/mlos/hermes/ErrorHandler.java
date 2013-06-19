package mlos.hermes;

public interface ErrorHandler {
    
    boolean onError(CommandException e) throws CommandException;

}
