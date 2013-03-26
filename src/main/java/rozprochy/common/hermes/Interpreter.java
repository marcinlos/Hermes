package rozprochy.common.hermes;

public interface Interpreter {
    
    boolean interpret(String line) throws CommandException, ParseException;

}
