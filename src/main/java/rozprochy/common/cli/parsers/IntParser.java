package rozprochy.common.cli.parsers;

import rozprochy.common.cli.ParseException;
import rozprochy.common.cli.ValueParser;

public class IntParser implements ValueParser<Integer> {

    @Override
    public Integer parse(String value) throws ParseException {
        try {
            return Integer.decode(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid integer `" + value + "'", e);
        }
    }

}
