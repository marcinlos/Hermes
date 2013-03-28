package rozprochy.common.hermes.parsers;

import rozprochy.common.hermes.parsing.ParseException;
import rozprochy.common.hermes.parsing.ValueParser;

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
