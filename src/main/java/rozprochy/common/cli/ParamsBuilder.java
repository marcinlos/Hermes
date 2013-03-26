package rozprochy.common.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParamsBuilder {

    private List<String> nonopts = new ArrayList<String>();
    private Map<String, String> opts = new HashMap<String, String>();
    
    public ParamsBuilder add(String item) throws ParseException {
        if (item.startsWith("--")) {
            // long options
            String opt = null;
            String value = null;
            int pos = item.indexOf('=');
            if (pos != -1) {
                String[] pair = item.split("=", 2);
                opt = pair[0];
                value = pair[1];
            } else {
                opt = item;
            }
            opts.put(opt, value);
        } else if (item.startsWith("-")) {
            // short option
            for (char c: item.substring(1).toCharArray()) {
                if (Character.isLetter(c)) {
                    opts.put("-" + c, null);
                } else {
                    throw new ParseException("Invalid character `" + c +
                            "' in short option pack `" + item + "'");
                }
            }
        } else {
            // non-opt argument
            nonopts.add(item);
        }
        return this;
    }
    
    public ParamsBuilder add(String... items) throws ParseException {
        for (String item: items) {
            add(item);
        }
        return this;
    }
    
    public ParamsBuilder add(Iterable<String> items) throws ParseException {
        for (String item: items) {
            add(item);
        }
        return this;
    }
    
    public ParamsBuilder add(Iterator<String> it) throws ParseException {
        while (it.hasNext()) {
            add(it.next());
        }
        return this;
    }
    
    public Parameters build() {
        return new Parameters(nonopts, opts);
    }

}
