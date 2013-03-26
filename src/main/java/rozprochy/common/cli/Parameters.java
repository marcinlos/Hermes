package rozprochy.common.cli;

import java.util.List;
import java.util.Map;

/**
 * Structure holding results of initial command parsing - non-option words
 * are contained in the list, option words and, possibly corresponding values,
 * are contained in the map. Options with no arguments have value {@code null}.
 * 
 * @author los
 */
public class Parameters {
    
    private List<String> nonopts;
    private Map<String, String> opts;

    public Parameters(List<String> nonopts, Map<String, String> opts) {
        this.nonopts = nonopts;
        this.opts = opts;
    }
    
    public boolean has(String param) {
        return opts.containsKey(param);
    }
    
    public String get(String param) {
        return opts.get(param);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int n = 0;
        for (String word: nonopts) {
            sb.append(word);
            if (++ n < nonopts.size()) {
                sb.append(", ");
            }
        }
        if (! opts.isEmpty()) {
            sb.append(" | ");
            n = 0;
            for (Map.Entry<String, String> opt: opts.entrySet()) {
                sb.append(opt.getKey()).append(':');
                if (opt.getValue() != null) {
                    sb.append(opt.getValue());
                } else {
                    sb.append('-');
                }
                if (++ n < opts.size()) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public List<String> getNonopts() {
        return nonopts;
    }

    public void setNonopts(List<String> nonopts) {
        this.nonopts = nonopts;
    }
    
    public Map<String, String> getOpts() {
        return opts;
    }

}
