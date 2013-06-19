package mlos.hermes.parsing;

import java.util.HashMap;
import java.util.Map;


/**
 * Naive implementation of parser factory. Reflectively creates instances of
 * parser classes each time the parser is requested. Which sucks really hard,
 * so:
 * TODO: Make it more efficient
 * 
 * @author los
 */
class ParserFactoryImpl implements ParserFactory {
    
    private Map<Class<?>, Class<? extends ValueParser<?>>> parserClasses = 
            new HashMap<Class<?>, Class<? extends ValueParser<?>>>();
    
    private ParserFactory parent;
    
    public ParserFactoryImpl(ParserFactory parent) {
        this.parent = parent;
    }

    public <T> void registerParser(Class<T> clazz,
            Class<? extends ValueParser<? extends T>> parser) {
        parserClasses.put(clazz, parser);
    }

    @Override
    public <T> ValueParser<? extends T> getParser(Class<T> clazz)
            throws ParserFactoryException {
        Class<? extends ValueParser<?>> parserCls = parserClasses.get(clazz);
        if (parserCls != null) {
            try {
                @SuppressWarnings("unchecked")
                ValueParser<? extends T> parser = 
                (ValueParser<? extends T>) parserCls.newInstance();
                return parser;
            } catch (InstantiationException e) {
                throw new ParserFactoryException(e);
            } catch (IllegalAccessException e) {
                throw new ParserFactoryException(e);
            }
        } else if (parent != null) {
            return parent.getParser(clazz);
        } else {
            throw new MissingParserException(clazz);
        }
    }

}
