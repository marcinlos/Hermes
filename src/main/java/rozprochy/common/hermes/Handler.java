package rozprochy.common.hermes;

import java.util.List;


/**
 * Commmon interface for all the commands. Used by the {@linkplain Interpreter}
 * to dispatch command invocations.
 * 
 * @author los
 */
public interface Handler {

    boolean execute(List<String> name, Parameters params)
            throws CommandException;

}
