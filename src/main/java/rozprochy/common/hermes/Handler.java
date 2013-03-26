package rozprochy.common.hermes;

import java.util.List;


/**
 * Commmon interface for all the commands. Used by the 
 * 
 * @author los
 */
public interface Handler {

    boolean execute(List<String> name, Parameters params)
            throws CommandException;

}
