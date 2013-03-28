package rozprochy.common.hermes.parsers;

import rozprochy.common.hermes.parsing.ParseException;
import rozprochy.common.hermes.parsing.ValueParser;

public class StringParser implements ValueParser<String> {

    @Override
    public String parse(String value) throws ParseException {
        // TODO: Unescape, maybe?
        return value;
    }

}
