package rozprochy.common.cli.parsers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import rozprochy.common.cli.ParseException;
import rozprochy.common.cli.ValueParser;

public class InetAddressParser implements ValueParser<InetAddress> {

    @Override
    public InetAddress parse(String value) throws ParseException {
        try {
            return InetAddress.getByName(value);
        } catch (UnknownHostException e) {
            throw new ParseException("Unknown host `" + value + "'", e);
        }
    }

}
