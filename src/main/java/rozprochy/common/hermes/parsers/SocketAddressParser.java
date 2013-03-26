package rozprochy.common.hermes.parsers;

import java.net.InetSocketAddress;

import rozprochy.common.hermes.ParseException;
import rozprochy.common.hermes.ValueParser;

/**
 * Parses transport layer TCP/UDP addresses of the form {@code host:port}, e.g.
 * <ul>
 * <li>{@code 192.168.0.15:6666}
 * <li>{@code google.com:80}
 * </ul>
 * <p>
 * The host name IS NOT resolved during parsing. See 
 * {@linkplain InetSocketAddress#createUnresolved(String, int)} for details.
 * 
 * @author los
 */
public class SocketAddressParser implements ValueParser<InetSocketAddress> {

    @Override
    public InetSocketAddress parse(String value) throws ParseException {
        int pos = value.indexOf(':');
        if (pos == -1) {
            throw new ParseException("Invalid socket address `" + value + "'");
        } else {
            String[] parts = value.split(":");
            String host = parts[0];
            int port = 0;
            try {
                port = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid port `" + parts[1] + "'", e);
            }
            if (port < 0) {
                throw new ParseException("Negative port number " + port);
            }
            return InetSocketAddress.createUnresolved(host, port);
        }
    }
    
}
