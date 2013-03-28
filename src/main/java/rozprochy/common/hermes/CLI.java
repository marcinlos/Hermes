package rozprochy.common.hermes;

import rozprochy.common.hermes.impl.CommandInfo;
import rozprochy.common.hermes.impl.HandlerImpl;
import rozprochy.common.hermes.impl.MethodMapper;
import rozprochy.common.hermes.impl.Trie;

public class CLI {

    private CLI() { }
    
    public static Interpreter create(Object... objects) {
        
        SimpleInterpreter interpreter = new SimpleInterpreter();
        
        for (Object o: objects) {
            Class<?> clazz = o.getClass();
            Trie<String, CommandInfo> commands = MethodMapper.extract(clazz);
            for (CommandInfo cmd: commands.values()) {
                HandlerImpl handler = new HandlerImpl(o, cmd.getMethod());
                interpreter.registerHandler(handler, cmd.getName());
            }
        }
        return interpreter;
    }

}
