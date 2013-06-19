package mlos.hermes.parsers;

import mlos.hermes.parsing.ParseException;
import mlos.hermes.parsing.ValueParser;

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
