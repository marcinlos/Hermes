package rozprochy.common.cli.parsers;

import rozprochy.common.cli.ParseException;
import rozprochy.common.cli.ValueParser;

public class StringParser implements ValueParser<String> {

    @Override
    public String parse(String value) throws ParseException {
        // TODO: Unescape, maybe?
        return value;
    }

}
