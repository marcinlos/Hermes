package mlos.hermes.parsing;


public interface ValueParser<T> {
    T parse(String value) throws ParseException;
}
