package rozprochy.common.cli;

import java.util.Scanner;

/**
 * Class responsible for transforming the raw input lines into structured,
 * easy to use parameter description. 
 * <p>
 * For details about the input format, see {@linkplain Parameters}.
 *  
 * @author los
 * @see Parameters
 * @see ParamsBuilder
 */
public class CommandParser {
    
    /**
     * Processes the input line and creates a {@linkplain Parameters} structure
     * based on its content.
     * 
     * @param line input string 
     * @return {@linkplain Parameters} structure describing the 
     * @throws ParseException
     */
    public Parameters parse(String line) throws ParseException {
        Scanner scanner = new Scanner(line);
        return new ParamsBuilder().add(scanner).build();
    }

}
