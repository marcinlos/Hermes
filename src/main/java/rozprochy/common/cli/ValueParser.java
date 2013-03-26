package rozprochy.common.cli;

public interface ValueParser<T> {
    T parse(String value) throws ParseException;
}
