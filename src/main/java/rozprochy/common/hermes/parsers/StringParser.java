package rozprochy.common.hermes.parsers;

import rozprochy.common.hermes.ParseException;
import rozprochy.common.hermes.ValueParser;

public class StringParser implements ValueParser<String> {

    @Override
    public String parse(String value) throws ParseException {
        // TODO: Unescape, maybe?
        return value;
    }

}
