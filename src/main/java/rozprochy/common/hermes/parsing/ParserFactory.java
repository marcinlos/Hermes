package rozprochy.common.hermes.parsing;


/**
 * Factory providing parsers for command-line parameter values.
 * 
 * @author los
 */
public interface ParserFactory {

    <T> ValueParser<? extends T> getParser(Class<T> clazz)
            throws ParserFactoryException;

}
