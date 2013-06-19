package mlos.hermes.parsers;

import mlos.hermes.parsing.ParseException;
import mlos.hermes.parsing.ValueParser;

public class StringParser implements ValueParser<String> {

    @Override
    public String parse(String value) throws ParseException {
        // TODO: Unescape, maybe?
        return value;
    }

}
