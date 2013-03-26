package rozprochy.common.cli;

/**
 * Exception thrown upon failure of command invocation. Used to wrap exception
 * thrown by the invocation.
 * 
 * @author los
 */
public class CommandExecutionException extends CommandException {

    public CommandExecutionException(Throwable cause) {
        super(cause);
    }

    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}
