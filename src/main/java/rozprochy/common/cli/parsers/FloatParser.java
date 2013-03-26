package rozprochy.common.cli.parsers;

import rozprochy.common.cli.ParseException;
import rozprochy.common.cli.ValueParser;

public class FloatParser implements ValueParser<Double> {

    @Override
    public Double parse(String value) throws ParseException {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid floating point number `" + 
                    value + "'", e);
        }
    }

}
