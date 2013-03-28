package rozprochy.common.hermes.parsing;

import java.net.InetAddress;
import java.net.SocketAddress;

import rozprochy.common.hermes.parsers.FloatParser;
import rozprochy.common.hermes.parsers.InetAddressParser;
import rozprochy.common.hermes.parsers.IntParser;
import rozprochy.common.hermes.parsers.SocketAddressParser;
import rozprochy.common.hermes.parsers.StringParser;

public class Parsers {

    private static final ParserFactory root;
    
    private Parsers() { 
        //Non-instantiable
    }
    
    static {
        ParserFactoryImpl fac = new ParserFactoryImpl(null);
        fac.registerParser(Integer.class, IntParser.class);
        fac.registerParser(String.class, StringParser.class);
        fac.registerParser(Double.class, FloatParser.class);
        fac.registerParser(InetAddress.class, InetAddressParser.class);
        fac.registerParser(SocketAddress.class, SocketAddressParser.class);
        
        root = fac;
    }
    
    public static ParserFactory getDefaultParserFactory() {
        return root;
    }
    
    public static ParserFactory getParserFactory(boolean detached) {
        return new ParserFactoryImpl(detached ? null : root);
    }

}
