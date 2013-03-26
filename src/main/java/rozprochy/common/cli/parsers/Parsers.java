package rozprochy.common.cli.parsers;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import rozprochy.common.cli.ValueParser;

public class Parsers {

    private static final Map<Class<?>, Class<? extends ValueParser<?>>> defaults;
    
    private Parsers() { 
        //Non-instantiable
    }
    
    static {
        
        defaults = new HashMap<Class<?>, Class<? extends ValueParser<?>>>();
        defaults.put(Integer.class, IntParser.class);
        defaults.put(String.class, StringParser.class);
        defaults.put(Double.class, FloatParser.class);
        defaults.put(InetAddress.class, InetAddressParser.class);
        defaults.put(SocketAddress.class, SocketAddressParser.class);
        
    }
    
    @SuppressWarnings("unchecked")
    public static <T> ValueParser<T> getDefaultParser(Class<T> clazz) {
        try {
            return (ValueParser<T>) defaults.get(clazz).newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}
