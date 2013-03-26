package rozprochy.common.hermes;

public interface ValueParser<T> {
    T parse(String value) throws ParseException;
}
