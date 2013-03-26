package rozprochy.common.cli;

import rozprochy.common.cli.impl.CommandInfo;
import rozprochy.common.cli.impl.HandlerImpl;
import rozprochy.common.cli.impl.MethodMapper;

public class CLI {

    private CLI() { }
    
    public static Interpreter create(Object... objects) {
        
        SimpleInterpreter interpreter = new SimpleInterpreter();
        
        for (Object o: objects) {
            Class<?> clazz = o.getClass();
            Trie<String, CommandInfo> commands = MethodMapper.extract(clazz);
            for (CommandInfo cmd: commands.values()) {
                HandlerImpl handler = new HandlerImpl(o, cmd.method);
                interpreter.registerHandler(handler, cmd.name);
            }
        }
        return interpreter;
    }

}
