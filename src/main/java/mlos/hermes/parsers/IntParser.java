package mlos.hermes.parsers;

import mlos.hermes.parsing.ParseException;
import mlos.hermes.parsing.ValueParser;

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
