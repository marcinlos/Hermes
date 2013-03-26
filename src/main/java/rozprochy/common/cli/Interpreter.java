package rozprochy.common.cli;

public interface Interpreter {
    
    boolean interpret(String line) throws CommandException, ParseException;

}
