package rozprochy.common.cli;

import java.util.List;


public class SimpleInterpreter implements Interpreter {
    
    private Trie<String, Handler> handlers = new Trie<String, Handler>();
    private final CommandParser parser = new CommandParser();
    
    public void registerHandler(Handler handler, String... name) {
        handlers.put(handler, name);
    }
    
    @Override
    public boolean interpret(String line) throws ParseException, 
            CommandException {
        Parameters call = parser.parse(line);
        List<String> nonopts = call.getNonopts();
        if (! nonopts.isEmpty()) {
            Trie<String, Handler>.Partial res = handlers.getByPrefix(nonopts);
            call.setNonopts(res.rest);
            
            Handler handler = res.result.getValue();
            if (handler != null) { 
                return handler.execute(res.consumed, call);
            } else {
                throw new NoSuchCommandException(call.toString());
            }
        }
        return true;
    }

}
